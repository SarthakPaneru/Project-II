package com.example.hamro_barber.service;

import com.example.hamro_barber.entity.Services;
import com.example.hamro_barber.helper.ApiResponse;

import java.util.List;

public interface ServiceService {
    Services createService(Services services);
    List<Services> getAllServices();
    Services getService(Integer serviceId);
    Services updateService(Services services);
    ApiResponse deleteService(Integer serviceId);

}
