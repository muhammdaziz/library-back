package com.example.libraryback.service.report;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.report.ReportDTO;
import com.example.libraryback.payload.website.WebsiteDTO;
import com.example.libraryback.payload.website.WebsiteEditDTO;

import java.util.List;

public interface ReportService {

    ApiResult<List<ReportDTO>> get();

    ApiResult<WebsiteDTO> getWebsite();

    ApiResult<WebsiteDTO> setWebsite(WebsiteEditDTO websiteEditDTO);
}
