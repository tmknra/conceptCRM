package pack.concept.file_service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileOutDto {

    private Long id;
    private String name;
    private String link;

}
