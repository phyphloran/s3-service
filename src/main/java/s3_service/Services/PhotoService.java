package s3_service.Services;


import s3_service.Dtos.PhotoDto;
import s3_service.Requests.AddPhotoRequest;


public interface PhotoService {

    PhotoDto savePhoto(AddPhotoRequest addPhotoRequest);

}
