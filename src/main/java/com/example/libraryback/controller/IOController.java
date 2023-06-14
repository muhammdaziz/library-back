package com.example.libraryback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("api/file")
public interface IOController {
    @GetMapping("/download/{id}")
    void download(@PathVariable UUID id, HttpServletResponse response) throws IOException;

    @GetMapping("/image/{id}")
    void serveImage(@PathVariable UUID id, HttpServletResponse response) throws IOException;
}
