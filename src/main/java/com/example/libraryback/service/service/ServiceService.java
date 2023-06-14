package com.example.libraryback.service.service;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.service.ServiceAddDTO;
import com.example.libraryback.payload.service.ServiceDTO;

import java.util.List;

public interface ServiceService {

    ApiResult<?> delete(Integer id);

    ApiResult<List<ServiceDTO>> get();

    ApiResult<ServiceDTO> get(Integer id);

    ApiResult<ServiceDTO> add(ServiceAddDTO serviceAddDTO);

    ApiResult<ServiceDTO> edit(Integer id, ServiceAddDTO serviceAddDTO);

}
