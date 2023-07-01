package com.example.hamro_barber.mapper;

import com.example.hamro_barber.entity.Services;
import com.example.hamro_barber.entity.User;
import com.example.hamro_barber.entity.dto.ServiceDto;
import com.example.hamro_barber.entity.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceDto serviceToDto(Services service);
    Services dtoToService(ServiceDto serviceDto);
    List<ServiceDto> listServiceToDto(List<Services> services);
    List<Services> listDtoToService(List<ServiceDto> serviceDtos);
}
