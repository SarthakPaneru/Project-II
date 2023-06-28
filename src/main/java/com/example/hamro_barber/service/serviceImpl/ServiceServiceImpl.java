package com.example.hamro_barber.service.serviceImpl;

import com.example.hamro_barber.entity.Services;
import com.example.hamro_barber.exception.ResourceNotFoundException;
import com.example.hamro_barber.helper.ApiResponse;
import com.example.hamro_barber.repository.ServiceRepository;
import com.example.hamro_barber.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    @Override
    public Services createService(Services services) {
        return serviceRepository.save(services);
    }

    @Override
    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Services getService(Integer serviceId) {
        Optional<Services> services = serviceRepository.findById(serviceId);
        if (services.isPresent()) {
            return services.get();
        } else {
            throw new ResourceNotFoundException("No such service available");
        }
    }

    @Override
    public Services updateService(Services services) {
        Services existingServices = getService(services.getId());
        existingServices.setServiceName(services.getServiceName());
        existingServices.setFee(services.getFee());
        existingServices.setServiceTimeInMinutes(services.getServiceTimeInMinutes());
        existingServices = serviceRepository.save(existingServices);
        return existingServices;
    }

    @Override
    public ApiResponse deleteService(Integer serviceId) {
        Services existingServices = getService(serviceId);
        serviceRepository.delete(existingServices);
        return new ApiResponse(true, "Service deleted successfully");
    }
}
