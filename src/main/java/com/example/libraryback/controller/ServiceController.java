package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.service.ServiceAddDTO;
import com.example.libraryback.payload.service.ServiceDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/service")
public interface ServiceController {

    @GetMapping()
    ApiResult<List<ServiceDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);

    @GetMapping("/{id}")
    ApiResult<ServiceDTO> get(@PathVariable Integer id);

    @PostMapping()
    ApiResult<ServiceDTO> add(@NotNull @ModelAttribute ServiceAddDTO serviceAddDTO);

    @PutMapping("/{id}")
    ApiResult<ServiceDTO> edit(@PathVariable Integer id, @RequestBody ServiceAddDTO serviceAddDTO);


}
