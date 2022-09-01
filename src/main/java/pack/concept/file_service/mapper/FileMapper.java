package pack.concept.file_service.mapper;

import pack.concept.file_service.dto.FileInDto;
import pack.concept.file_service.dto.FileOutDto;
import pack.concept.file_service.model.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class FileMapper {

    @Mapping(target = "id", ignore = true)
    public abstract FileEntity fileInDtoToFileEntity(FileInDto fileInDto);

    public abstract FileOutDto fileToFileOutDto(FileEntity file);

}
