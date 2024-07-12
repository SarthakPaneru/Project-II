package com.example.hamro_barber.service.serviceImpl;

import com.example.hamro_barber.model.Appointment;
import com.example.hamro_barber.service.AppointmentService;
import com.example.hamro_barber.service.ServicesService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@EnableScheduling
public class Scheduler {
    private final AppointmentService appointmentService;

    @Scheduled(fixedRate = 30000)
    public void scheduledTask() {
        List<Appointment> appointments = appointmentService.findNext30MinutesAppointment();
        for (Appointment appointment : appointments) {
            String barberDeviceId = appointment.getBarber().getUser().getDeviceIdFirebase();
            String customerDeviceId = appointment.getCustomer().getUser().getDeviceIdFirebase();

            sendNotification(barberDeviceId);
            sendNotification(customerDeviceId);

            appointmentService.updateNotificationStatus(appointment.getId());
        }
    }

    public void sendNotification(String registrationToken) {
        // See documentation on defining a message payload.
        System.out.println("Sending notification to " + registrationToken);
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle("Normal Haircut")
                        .setBody("User: Bhoju")
                        .build())
                .setToken(registrationToken)
                .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            // Response is a message ID string.
            System.out.println("Successfully sent message: " + response);
        }catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
}
