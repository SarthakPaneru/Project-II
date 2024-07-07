package com.example.hamro_barber.socket;

import com.example.hamro_barber.model.Barber;
import com.example.hamro_barber.model.Customer;
import com.example.hamro_barber.model.dto.SocketDto;
import com.example.hamro_barber.service.AppointmentService;
import com.example.hamro_barber.service.BarberService;
import com.example.hamro_barber.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SocketService {
    private final SocketTopics socketTopics;
    private final CustomerService customerService;
    private final BarberService barberService;
    private final AppointmentService appointmentService;

    public void test() {
//        socketTopics.hello();
    }

    public void sendMessageToCustomer(Map<String, Object> message, Integer id) {
        Customer customer = customerService.findCustomerById(id);
        System.out.println("Customer: " + customer.toString());
        socketTopics.toCustomer(customer.toString(), id);
    }

    public void sendMessageToNearbyBarbers(Integer customerId, SocketDto socketDto) {
//        Customer customer = customerService.findCustomerById(customerId);
        List<Barber> barbers = barberService.findNearestBarbers(socketDto.getLatitude(), socketDto.getLongitude());
        for (Barber barber : barbers) {
            socketTopics.toBarber(socketDto, barber.getId());
        }

    }
}
