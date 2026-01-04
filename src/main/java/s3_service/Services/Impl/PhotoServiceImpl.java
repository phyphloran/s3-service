package s3_service.Services.Impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;
import s3_service.Dtos.PhotoDto;
import s3_service.ExceptionHandler.Exceptions.PhotoUploadException;
import s3_service.Requests.AddPhotoRequest;
import s3_service.Services.PhotoService;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.util.UUID;


@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final S3Client s3Client;

    @Value("${s3.bucket-name}")
    private String bucketName;

    @Value("${s3.photo-folder}")
    private String photoFolder;

    @Value("${s3.ip}")
    private String ip;

    @Value("${s3.port}")
    private String port;

    @Value("${s3.secure}")
    private boolean secure;

    @Value("${spring.servlet.multipart.max-file-size}")
    private DataSize maxFileSize;

    @Override
    public PhotoDto savePhoto(AddPhotoRequest addPhotoRequest) {
        MultipartFile file = addPhotoRequest.file();
        try {
            String fileName = generateFileName(file);
            String objectKey = photoFolder + "/" + fileName;
            PutObjectRequest request = buildS3Request(file, objectKey);
            s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
            return generatePhotoDto(fileName);
        } catch (Exception e) {
            log.error("Photo upload failed", e);
            throw new PhotoUploadException("Photo Upload Error");
        }
    }

    private PhotoDto generatePhotoDto(String fileName) {
        String objectKey = photoFolder + "/" + fileName;
        String photoUrl = String.format("%s%s:%s/%s/%s",
                secure ? "https://" : "http://",
                ip,
                port,
                bucketName,
                objectKey
        );
        return new PhotoDto(photoUrl);
    }

    private PutObjectRequest buildS3Request(MultipartFile file, String objectKey) {
        return PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .contentType(file.getContentType())
                .build();
    }

    private String generateFileName(MultipartFile file) {
        return UUID.randomUUID() + "-" + file.getOriginalFilename();
    }

}
