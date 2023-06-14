package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.service.ServiceAddDTO;
import com.example.libraryback.payload.service.ServiceDTO;
import com.example.libraryback.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServiceControllerImpl implements ServiceController {

    private final ServiceService serviceService;

    @Override
    public ApiResult<?> delete(Integer id){
        return serviceService.delete(id);
    }

    @Override
    public ApiResult<List<ServiceDTO>> get(){
        return serviceService.get();
    }

    @Override
    public ApiResult<ServiceDTO> get(Integer id){
        return serviceService.get(id);
    }

    @Override
    public ApiResult<ServiceDTO> add(ServiceAddDTO serviceAddDTO){
        return serviceService.add(serviceAddDTO);
    }

    @Override
    public ApiResult<ServiceDTO> edit(Integer id, ServiceAddDTO serviceAddDTO){
        return serviceService.edit(id, serviceAddDTO);
    }


}
