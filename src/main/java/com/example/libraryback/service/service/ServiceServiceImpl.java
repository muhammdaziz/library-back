package com.example.libraryback.service.service;

import com.example.libraryback.entity.Services;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.service.ServiceAddDTO;
import com.example.libraryback.payload.service.ServiceDTO;
import com.example.libraryback.repository.ServiceRepository;
import com.example.libraryback.service.io.IOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final IOService ioService;
    private final ServiceRepository serviceRepository;

    @Override
    public ApiResult<?> delete(Integer id){

        checkExist(id);

        serviceRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<List<ServiceDTO>> get(){

        List<Services> services = serviceRepository.findAll();

        return ApiResult
                .successResponse(
                        mapServiceDTO(services));
    }

    @Override
    public ApiResult<ServiceDTO> get(Integer id){

        Services service = checkExist(id);

        return ApiResult
                .successResponse(
                        mapServiceDTO(service));
    }

    @Override
    public ApiResult<ServiceDTO> add(ServiceAddDTO serviceAddDTO){

        checkExistByTitle(serviceAddDTO.getTitle());

        Services service = new Services();

        mapService(serviceAddDTO, service);

        try {
            service.setImage(
                    ioService
                            .upload(
                                    serviceAddDTO.getImage(),
                                    true)
            );
        } catch (IOException e) {
            throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
        }

        serviceRepository.save(service);

        return ApiResult
                .successResponse(
                        mapServiceDTO(service));
    }

    @Override
    public ApiResult<ServiceDTO> edit(Integer id, ServiceAddDTO serviceAddDTO){

        Services service = checkExist(id);

        checkExistByTitleNotId(serviceAddDTO.getTitle(), id);

        mapService(serviceAddDTO, service);

        if (!Objects.isNull(serviceAddDTO.getImage()))
            try {
                service.setImage(
                        ioService
                                .upload(
                                        serviceAddDTO.getImage(),
                                        true)
                );
            } catch (IOException e) {
                throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
            }

        serviceRepository.save(service);

        return ApiResult
                .successResponse(
                        mapServiceDTO(service));
    }


    private Services checkExist(Integer id) {

        return serviceRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO PROMOTION FOUND", HttpStatus.NOT_FOUND));
    }

    private void checkExistByTitle(String title) {

        if(serviceRepository.existsByTitle(title))
            throw new RestException("ALREADY EXIST WITH TITLE", HttpStatus.CONFLICT);
    }

    private ServiceDTO mapServiceDTO(Services service) {

        return ServiceDTO
                .builder()
                .id(service.getId())
                .title(service.getTitle())
                .subtitle(service.getSubtitle())
                .svg(service.getImage().getId())
                .build();
    }

    private void mapService(ServiceAddDTO serviceAddDTO, Services service) {

        service.setTitle(serviceAddDTO.getTitle());
        service.setSubtitle(serviceAddDTO.getSubtitle());
    }

    private void checkExistByTitleNotId(String title, Integer id) {

        serviceRepository.findByTitleAndNotId(title, id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("ALREADY EXIST WITH TITLE", HttpStatus.CONFLICT));
    }

    private List<ServiceDTO> mapServiceDTO(List<Services> services) {

        return services
                .stream()
                .map(this::mapServiceDTO)
                .collect(Collectors.toList());
    }

}
