package com.example.hamro_barber.service.serviceImpl;

import com.example.hamro_barber.model.Barber;
import com.example.hamro_barber.model.User;
import com.example.hamro_barber.exception.CustomException;
import com.example.hamro_barber.exception.ResourceNotFoundException;
import com.example.hamro_barber.helper.ApiResponse;
import com.example.hamro_barber.repository.BarberRepository;
import com.example.hamro_barber.service.BarberService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BarberServiceImpl implements BarberService {
    private final BarberRepository barberRepository;
    private final UserServiceImpl userService;
    private final Path path = Paths.get("images");

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
    public Barber findBarberByEmail(String email) {
        System.out.println(email);
        return barberRepository.findBarberByUser_Email(email).orElseThrow(() -> new CustomException("Barber with such email not found"));
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

    @Override
    public void saveImage(MultipartFile file, Integer barberId) {
        try {
            Files.copy(file.getInputStream(), this.path.resolve(file.getOriginalFilename()));
            Barber barber = findBarberById(barberId);
            barber.setImageUrl("images/" + file.getOriginalFilename());
            barberRepository.save(barber);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String load(Integer barberId) {
        Barber barber = findBarberById(barberId);
        String imageUrl = barber.getImageUrl();
        System.out.println(imageUrl);
        try {
            File file = new ClassPathResource(
                    "static/" + imageUrl).getFile();
            System.out.println(file.toURI());
            Resource resource = new UrlResource(file.toURI());

            if (resource.exists() || resource.isReadable()) {
                System.out.println("Img: " + imageUrl);
                return imageUrl;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
