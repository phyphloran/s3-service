package s3_service.Controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3_service.Dtos.PhotoDto;
import s3_service.Requests.AddPhotoRequest;
import s3_service.Services.PhotoService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/photos")
public class PhotoController {
    
    private final PhotoService photoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PhotoDto> savePhoto(@Valid @ModelAttribute AddPhotoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(photoService.savePhoto(request));
    }

}
