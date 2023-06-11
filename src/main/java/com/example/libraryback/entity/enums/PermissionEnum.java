package com.example.libraryback.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum PermissionEnum implements GrantedAuthority {

    ADD_BOOK(PageEnum.BOOK),
    EDIT_BOOK(PageEnum.BOOK),
    GET_BOOK(PageEnum.BOOK),
    DELETE_BOOK(PageEnum.BOOK),
    ADD_CATEGORY(PageEnum.CATEGORY),
    EDIT_CATEGORY(PageEnum.CATEGORY),
    GET_CATEGORY(PageEnum.CATEGORY),
    DELETE_CATEGORY(PageEnum.CATEGORY),
    ADD_AUTHOR(PageEnum.AUTHOR),
    EDIT_AUTHOR(PageEnum.AUTHOR),
    GET_AUTHOR(PageEnum.AUTHOR),
    DELETE_AUTHOR(PageEnum.AUTHOR),
    ADD_POPULAR_BOOK(PageEnum.BOOK),
    EDIT_POPULAR_BOOK(PageEnum.BOOK),
    GET_POPULAR_BOOK(PageEnum.BOOK),
    DELETE_POPULAR_BOOK(PageEnum.BOOK),

    HOME(PageEnum.HOME),

    DASHBOARD(PageEnum.DASHBOARD),
    SET_ROLE_TO_USER(PageEnum.ROLE);

    private final PageEnum page;

    @Override
    public String getAuthority() {
        return name();
    }
}
