package s3_service.Services;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import s3_service.Annotations.ValidImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Set;

public class ImageFileValidator implements ConstraintValidator<ValidImage, MultipartFile> {

    @Value("${spring.servlet.multipart.max-file-size}")
    private long MAX_FILE_SIZE;

    private final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg",
            "image/jpg",
            "image/png",
            "image/webp",
            "image/gif",
            "image/bmp",
            "image/tiff",
            "image/heic",
            "image/heif"
    );

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null) {
            buildViolation(context, "File is required");
            return false;
        }
        if (file.isEmpty()) {
            buildViolation(context, "File cannot be empty");
            return false;
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            buildViolation(context, "File size exceeds maximum allowed: " + MAX_FILE_SIZE + " bytes");
            return false;
        }
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            buildViolation(context, "File type not allowed: " + file.getContentType());
            return false;
        }
        try {
            if (ImageIO.read(file.getInputStream()) == null) {
                buildViolation(context, "File is not a valid image");
                return false;
            }
        } catch (IOException e) {
            buildViolation(context, "Unable to read file");
            return false;
        }
        return true;
    }

    private void buildViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
