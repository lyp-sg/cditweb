package com.yongpeng.dev.cditweb.Controller;

import com.yongpeng.dev.cditweb.DTO.UserDTO;
import com.yongpeng.dev.cditweb.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String displayUploadForm(Model model) {
        return "uploadForm";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        String uri = "http://localhost:8080/create/users";
        RestTemplate restTemplate = new RestTemplate();

        try {
            List<UserDTO> list = storageService.processFile(file);
            ResponseEntity<String> response = restTemplate.postForObject(uri, list, ResponseEntity.class);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message",
                    "Error with file: " + file.getOriginalFilename() + ". " + e.getMessage());
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

}
