package pack.concept.file_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack.concept.file_service.exception.StorageFileNotFoundException;
import pack.concept.file_service.service.StorageService;

import java.util.stream.Collectors;

@Controller
// @RequestMapping("/docs")
public class FileUploadControllerImpl  {

    private final StorageService storageService;

    @Autowired
    public FileUploadControllerImpl(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) {
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile",
                                path.getFileName().toString()).build().toUri().toString())
                        .collect(Collectors.toList()));
        return "uploadForm";
    }


    @GetMapping("/{fileName:.+}")
    @ResponseBody
    ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        Resource file = storageService.loadAsResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        System.out.println("HERE");
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " +
                file.getOriginalFilename() + "!");
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.badRequest().body(exc);
    }
}
