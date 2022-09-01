package pack.concept.file_service.controller;

import pack.concept.file_service.dto.FileInDto;
import pack.concept.file_service.dto.FileOutDto;
import pack.concept.file_service.model.FileEntity;

import java.util.List;

public class FileControllerImpl implements FileController {
    @Override
    public List<FileEntity> getAllFiles() {
        return null;
    }

    @Override
    public FileOutDto saveFile(FileInDto file) {
        return null;
    }

    @Override
    public Boolean deleteFileByName(String fileName) {
        return false;
    }
}
