package com.example.libraryback.service.io;

import com.example.libraryback.entity.FileImg;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public interface IOService {

    FileImg upload(MultipartFile file, boolean isImg) throws IOException;

    void download(UUID id, HttpServletResponse response) throws IOException;

    void serveImage(UUID id, HttpServletResponse response) throws IOException;
}
