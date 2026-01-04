package s3_service.ExceptionHandler.Exceptions;


public class PhotoUploadException extends RuntimeException {
    public PhotoUploadException(String message) {
        super(message);
    }
}
