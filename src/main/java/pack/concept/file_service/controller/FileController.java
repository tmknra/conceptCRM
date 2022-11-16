package pack.concept.file_service.controller;

import pack.concept.file_service.dto.FileInDto;
import pack.concept.file_service.dto.FileOutDto;
import pack.concept.file_service.model.FileEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docs")
public interface FileController {

    @GetMapping("/get")
    List<FileEntity> getAllFiles();

    @PostMapping("/add")
    FileOutDto saveFile(@RequestBody FileInDto file);

    @DeleteMapping("/delete/{fileName}")
    Boolean deleteFileByName(@PathVariable String fileName);
}
