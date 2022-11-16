package pack.concept.file_service.service;

import pack.concept.file_service.dto.FileInDto;
import pack.concept.file_service.dto.FileOutDto;
import pack.concept.file_service.exception.FileDoesNotExistException;
import pack.concept.file_service.mapper.FileMapper;
import pack.concept.file_service.model.FileEntity;
import pack.concept.file_service.reposiroty.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<FileOutDto> getAllFiles() {
        List<FileEntity> all = fileRepository.findAll();
        List<FileOutDto> files = new ArrayList<>();
        for (FileEntity fileEntity : all) {
            files.add(fileMapper.fileToFileOutDto(fileEntity));
        }
        return files;
    }

    @Override
    @Transactional
    public FileOutDto saveFile(FileInDto file) {
        FileEntity fileEntity = fileMapper.fileInDtoToFileEntity(file);
        FileEntity save = fileRepository.save(fileEntity);
        saveFileOnServer(save);
        return fileMapper.fileToFileOutDto(save);
    }

    @Override
    public Boolean deleteFileByName(String fileName) throws FileDoesNotExistException {
        Optional<FileEntity> byName = fileRepository.findByName(fileName);
        if (byName.isEmpty())
            throw new FileDoesNotExistException(fileName);
        return true;
    }

    private Boolean saveFileOnServer(FileEntity fileEntity) {
        // TODO: add dest url
        return new FileManager(fileEntity.getLink(), "dest url", 1024).copy();
    }
}
