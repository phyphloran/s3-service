package s3_service.Dtos;


import java.util.List;


public record ErrorDto(

        List<String> errorMessages

) {
}
