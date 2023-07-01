package com.example.hamro_barber.service.serviceImpl;

import com.example.hamro_barber.entity.Barber;
import com.example.hamro_barber.entity.User;
import com.example.hamro_barber.exception.ResourceNotFoundException;
import com.example.hamro_barber.helper.ApiResponse;
import com.example.hamro_barber.repository.BarberRepository;
import com.example.hamro_barber.service.BarberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BarberServiceImpl implements BarberService {
    private final BarberRepository barberRepository;
    private final UserServiceImpl userService;

    @Override
    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    @Override
    public Barber findBarberById(Integer barberId) {
        Optional<Barber> customer = barberRepository.findById(barberId);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Override
    public Barber createBarber(Barber barber) {
        User user = userService.findUserById(barber.getUser().getId());
        barber.setUser(user);
        return barberRepository.save(barber);
    }

    @Override
    public Barber updateBarber(Barber barber) {
        User user = userService.findUserByEmail(barber.getUser().getEmail());
        Barber existingBarber = findBarberById(barber.getId());
        User user1 = userService.findUserById(barber.getUser().getId());
        User updatedUser = userService.updateUser(user1);
        existingBarber.setUser(updatedUser);
        existingBarber.setOpened(barber.isOpened());
        System.out.println("Is opened: " + barber.isOpened());
        existingBarber.setServices(barber.getServices());
        existingBarber.setPanNo(barber.getPanNo());
        return barberRepository.save(existingBarber);
    }

    @Override
//    @Transactional(rollbackFor = SQLException.class)
    public ApiResponse deleteBarber(Integer customerId) {
        Optional<Barber> barber = barberRepository.findById(customerId);
        if (barber.isPresent()) {
            barberRepository.delete(barber.get());
//            userService.deleteUser(barber.get().getUser().getId());
            return new ApiResponse(true, "User successfully deleted");
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
