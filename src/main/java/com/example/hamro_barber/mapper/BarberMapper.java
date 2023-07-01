package com.example.hamro_barber.mapper;

import com.example.hamro_barber.entity.Barber;
import com.example.hamro_barber.entity.dto.BarberDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface BarberMapper {
    BarberDto barberToDto(Barber barber);
    Barber dtoToBarber(BarberDto barberDto);
    List<BarberDto> listBarberToDto(List<Barber> barbers);
    List<Barber> listDtoToBarber(List<BarberDto> barberDtos);
}
