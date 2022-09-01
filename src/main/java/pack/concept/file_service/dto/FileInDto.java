package pack.concept.file_service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileInDto {

    private String name;
    private String link;

}
