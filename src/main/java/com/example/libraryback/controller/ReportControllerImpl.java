package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.report.ReportDTO;
import com.example.libraryback.payload.website.WebsiteDTO;
import com.example.libraryback.payload.website.WebsiteEditDTO;
import com.example.libraryback.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ReportControllerImpl implements ReportController {

    private final ReportService reportService;

    @Override
    public ApiResult<List<ReportDTO>> getReport() {
        return reportService.get();
    }

    @Override
    public ApiResult<WebsiteDTO> getWebsite() {
        return reportService.getWebsite();
    }

    @Override
    public ApiResult<WebsiteDTO> setWebsite(WebsiteEditDTO websiteEditDTO) {
        return reportService.setWebsite(websiteEditDTO);
    }
}
