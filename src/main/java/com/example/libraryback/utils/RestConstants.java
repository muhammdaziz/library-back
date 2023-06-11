package com.example.libraryback.utils;

public interface RestConstants {
    String DEFAULT_PAGE_NUMBER = "0";
    String DEFAULT_PAGE_SIZE = "10";

    String UPLOAD_FILE = "/Users/user/IdeaProjects/lib/files/";

    String AUTHENTICATION_HEADER = "Authorization";

    String[] OPEN_PAGES = {
            "/*",
            "/api/auth" + "/**",
            "/api/book/list/**",
            "/api/book/{id}/**",
            "/api/files/**",
            "/api/category/list/**",
            "/api/category/{id}",
    };
}
