package s3_service.Requests;


import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import s3_service.Annotations.ValidImage;


public record AddPhotoRequest(

        @ValidImage
        @NotNull(message = "File can not be empty")
        MultipartFile file

) {
}
