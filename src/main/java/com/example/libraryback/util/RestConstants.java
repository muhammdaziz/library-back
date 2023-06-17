package com.example.libraryback.util;

import com.example.libraryback.controller.AuthController;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface RestConstants {
    ObjectMapper objectMapper = new ObjectMapper();

    String UPLOAD_FILE = "/Users/user/IdeaProjects/library/files/";

    String BASE_PATH = "/api/";

    String AUTHENTICATION_HEADER = "Authorization";

    String REFRESH_TOKEN = "RefreshToken";


    String[] OPEN_PAGES = {
            "/**",
            AuthController.AUTH_CONTROLLER_BASE_PATH + "/**",
            RestConstants.BASE_PATH + "/**"
    };

    //FUNKSIYA NI O'CHIRIB YANA YOZIB QO'YADI
    String QUERY_FOR_POSTGRES = "drop function if exists get_entity_id_list_for_generic_view(varchar);\n" +
            "create function get_entity_id_list_for_generic_view(sql_query varchar )\n" +
            "    returns TABLE\n" +
            "            (\n" +
            "                id_list    varchar\n" +
            "            )\n" +
            "    language plpgsql\n" +
            "as\n" +
            "$$\n" +
            "BEGIN\n" +
            "    RETURN QUERY\n" +
            "        EXECUTE sql_query;\n" +
            "END\n" +
            "$$;\n" +
            "alter function get_entity_id_list_for_generic_view(varchar) owner to postgres;";

}
