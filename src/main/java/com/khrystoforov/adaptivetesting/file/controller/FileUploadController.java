package com.khrystoforov.adaptivetesting.file.controller;

import com.khrystoforov.adaptivetesting.file.service.impl.ExcelParserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    private final ExcelParserService excelParserService;

    public FileUploadController(ExcelParserService excelParserService) {
        this.excelParserService = excelParserService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        try {
            excelParserService.parseAndSave(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
