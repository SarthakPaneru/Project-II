package com.example.hamro_barber.service;

import com.example.hamro_barber.entity.Barber;
import com.example.hamro_barber.helper.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface BarberService {
    List<Barber> getAllBarbers();
    Barber findBarberById(Integer barberId);
    Barber createBarber(Barber barber);
    Barber updateBarber(Barber barber);
    ApiResponse deleteBarber(Integer barberId);
    void saveImage(MultipartFile file, Integer barberId);
    String load(Integer barberId);
}
