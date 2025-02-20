package com.khrystoforov.adaptivetesting.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface ParserService {

    void parseAndSave(MultipartFile file) throws Exception;
}
