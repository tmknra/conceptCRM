package pack.concept.file_service.service;

import pack.concept.file_service.dto.FileInDto;
import pack.concept.file_service.dto.FileOutDto;
import pack.concept.file_service.exception.FileDoesNotExistException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {

    List<FileOutDto> getAllFiles();

    FileOutDto saveFile(FileInDto file);

    Boolean deleteFileByName(String fileName) throws FileDoesNotExistException;

}
