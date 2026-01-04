package s3_service.Annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import s3_service.Services.ImageFileValidator;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = ImageFileValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImage {

    String message() default "Invalid image file";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}