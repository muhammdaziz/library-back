package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.report.ReportDTO;
import com.example.libraryback.payload.website.WebsiteDTO;
import com.example.libraryback.payload.website.WebsiteEditDTO;
import com.example.libraryback.util.RestConstants;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = ReportController.REPORT_CONTROLLER_BASE_PATH)
public interface ReportController {

    String REPORT_CONTROLLER_BASE_PATH = RestConstants.BASE_PATH + "website";

    @GetMapping("/report")
    ApiResult<List<ReportDTO>> getReport();

    @GetMapping
    ApiResult<WebsiteDTO> getWebsite();

    @PutMapping
    ApiResult<WebsiteDTO> setWebsite(WebsiteEditDTO websiteEditDTO);
}
