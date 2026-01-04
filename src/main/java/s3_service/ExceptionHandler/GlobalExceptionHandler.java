package s3_service.ExceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import s3_service.Dtos.ErrorDto;
import s3_service.ExceptionHandler.Exceptions.PhotoUploadException;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorDto> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
            .body(new ErrorDto(List.of("The file is too big!")));
    }

    @ExceptionHandler(PhotoUploadException.class)
    public ResponseEntity<ErrorDto> entityNotFoundException(PhotoUploadException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(List.of(exception.getMessage())));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorDto> handleBindException(BindException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();
        return ResponseEntity
                .badRequest()
                .body(new ErrorDto(errorMessages));
    }

}
