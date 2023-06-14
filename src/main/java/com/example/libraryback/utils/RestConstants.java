package com.example.libraryback.utils;

public interface RestConstants {

    String UPLOAD_FILE = "/Users/user/IdeaProjects/library/files/";

    String[] OPEN_PAGES = {
            "/*",
            "/api/**",
            "/api/book/**",
            "/api/file/**",
            "/api/genre/**",
            "/api/discount/**",
            "/api/author/**",
            "/api/service/**",
            "/api/promotion/**",
            "/api/recommendation/**",
    };

    String DEFAULT_PAGE_SIZE = "10";

    String DEFAULT_PAGE_NUMBER = "0";

    String AUTHENTICATION_HEADER = "Authorization";

}
