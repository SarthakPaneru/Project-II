package com.example.hamro_barber.mapper;

import com.example.hamro_barber.model.Appointment;
import com.example.hamro_barber.model.dto.AppointmentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    AppointmentDto appointmentToDto(Appointment appointment);
    Appointment dtoToAppointment(AppointmentDto appointmentDto);
    List<AppointmentDto> listAppointmentToDto(List<Appointment> appointments);
    List<Appointment> listDtoToAppointment(List<AppointmentDto> appointmentDtos);
}
