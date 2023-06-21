package com.example.libraryback.service.report;

import com.example.libraryback.entity.Website;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.report.ReportDTO;
import com.example.libraryback.payload.website.WebsiteDTO;
import com.example.libraryback.payload.website.WebsiteEditDTO;
import com.example.libraryback.repository.AuthorRepository;
import com.example.libraryback.repository.BookRepository;
import com.example.libraryback.repository.WebsiteRepository;
import com.example.libraryback.service.io.IOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final IOService ioService;

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final WebsiteRepository websiteRepository;

    @Override
    public ApiResult<List<ReportDTO>> get() {

        List<ReportDTO> reports = new ArrayList<>();

        ReportDTO report = null;

        report = ReportDTO.builder()
                .title("Happy Customers")
                .value(getCustomersCount())
                .build();

        reports.add(report);

        report = ReportDTO.builder()
                .title("Book Collections")
                .value(getBookCount())
                .build();

        reports.add(report);

        report = ReportDTO.builder()
                .title("Our Stores")
                .value(getStoreCount())
                .build();

        reports.add(report);

        report = ReportDTO.builder()
                .title("Famous Writers")
                .value(getAuthorsCount())
                .build();

        reports.add(report);


        return ApiResult
                .successResponse(reports);
    }

    @Override
    public ApiResult<WebsiteDTO> getWebsite() {

        Website website = getWebsiteInfo();

        return ApiResult
                .successResponse(WebsiteDTO.map(website));
    }

    @Override
    public ApiResult<WebsiteDTO> setWebsite(WebsiteEditDTO websiteEditDTO) {

        Website website = getWebsiteInfo();

        website.setName(websiteEditDTO.getName());
        website.setPhone(websiteEditDTO.getPhone());
        website.setEmail(websiteEditDTO.getEmail());
        website.setAddress(websiteEditDTO.getAddress());
        website.setDescription(website.getDescription());
        website.setSubtitle(websiteEditDTO.getSubtitle());
        website.setWebpages(websiteEditDTO.getWebPages());
        website.setLocation(websiteEditDTO.getLocation());

        if (!Objects.isNull(websiteEditDTO.getLogo()))
            try {
                website.setLogo(
                        ioService
                                .upload(
                                        websiteEditDTO.getLogo(),
                                        true)
                );
            } catch (IOException e) {
                throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
            }

        websiteRepository.save(website);

        return ApiResult
                .successResponse();
    }

    private Website getWebsiteInfo() {
        return websiteRepository.find()
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO WEBSITE INFO", HttpStatus.NOT_FOUND));
    }

    private Long getCustomersCount() {
        return 125663L;
    }

    private Long getBookCount() {
        return bookRepository.count();
    }

    private Long getStoreCount() {
        return 1562L;
    }

    private Long getAuthorsCount() {
        return authorRepository.count();
    }
}
