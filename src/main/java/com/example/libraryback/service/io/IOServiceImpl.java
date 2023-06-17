package com.example.libraryback.service.io;

import com.example.libraryback.entity.FileImg;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.repository.BookRepository;
import com.example.libraryback.repository.FileRepository;
import com.example.libraryback.util.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

@Service
public class IOServiceImpl implements IOService{

    private final Path path;

    private static final int BUFFER_SIZE = 4096;

    private final BookRepository bookRepository;

    private final FileRepository fileRepository;

    private static final String UPLOAD_DIRECTORY = RestConstants.UPLOAD_FILE;

    public IOServiceImpl(BookRepository bookRepository, FileRepository fileRepository) {

        this.bookRepository = bookRepository;
        this.fileRepository = fileRepository;
        this.path = Paths
                .get(UPLOAD_DIRECTORY)
                .toAbsolutePath().normalize();

        createDirectories(path, "/img");
        createDirectories(path, "/pdf");
    }

    @Override
    public FileImg upload(MultipartFile originalFile, boolean isImg) {

        if (originalFile.isEmpty())
            throw RestException
                    .restThrow("No file chosen", HttpStatus.BAD_REQUEST);

        String url = isImg ? UPLOAD_DIRECTORY + "img" : UPLOAD_DIRECTORY + "pdf";

        Date date = new Date();

        Path path = Paths.get(url, date.getTime() + "-" +originalFile.getOriginalFilename());

        try {
            Files.write(path, originalFile.getBytes());
        } catch (IOException e) {
            throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
        }

        FileImg file = new FileImg();
        file.setName(isImg ? "image-" + date.getTime() : "book-" + date.getTime());
        file.setPath(path.toString());

        return fileRepository.save(file);
    }

    @Override
    public void download(UUID id, HttpServletResponse response) throws IOException {

        String filePath = getBookPath(id);

        File downloadFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());

        response.setHeader(headerKey, headerValue);

        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }

    @Override
    public void serveImage(UUID id, HttpServletResponse response) throws IOException {

        FileImg file = fileRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("FILE NOT FOUND", HttpStatus.NOT_FOUND)
                );

        String filePath = file.getPath();

        InputStream resource = new FileInputStream(filePath);

        response.setContentType(MediaType.ALL_VALUE);

        StreamUtils.copy(resource, response.getOutputStream());
    }



    private void createDirectories(Path path, String folder) {

        try {
            path = Paths
                    .get(path + folder)
                    .toAbsolutePath().normalize();

            Files.createDirectories(path);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    private String getBookPath(UUID id) {
        return bookRepository.findPathById(id);
    }
}
