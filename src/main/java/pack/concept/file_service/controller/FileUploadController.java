package pack.concept.file_service.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/docs")
public interface FileUploadController {

    @GetMapping("/")
    String listUploadedFiles(Model model);

    @GetMapping("/{fileName:.+}")
    @ResponseBody
    ResponseEntity<Resource> serveFile(@PathVariable String fileName);

    @PostMapping("/")
    String handleFileUpload(MultipartFile file
            , RedirectAttributes redirectAttributes);
}
