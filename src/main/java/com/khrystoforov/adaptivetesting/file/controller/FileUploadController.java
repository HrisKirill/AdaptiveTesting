package com.khrystoforov.adaptivetesting.file.controller;

import com.khrystoforov.adaptivetesting.file.service.impl.ExcelParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {
    private final ExcelParserService excelParserService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(name = "topicName") String topicName
    ) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        try {
            excelParserService.parseAndSave(file, topicName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
