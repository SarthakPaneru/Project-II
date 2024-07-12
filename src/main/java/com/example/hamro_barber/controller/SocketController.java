package com.example.hamro_barber.controller;

import com.example.hamro_barber.model.dto.SocketDto;
import com.example.hamro_barber.socket.SocketService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
//@RequestMapping("/socket")
@AllArgsConstructor
public class SocketController {

    private final SocketService service;

    @ResponseBody
    @GetMapping("/socket/push")
    public ResponseEntity<?> sendMessage(String m) throws FirebaseMessagingException {
        // This registration token comes from the client FCM SDKs.
        String registrationToken = "e1j9CY5oRCqFlyXKqmKva5:APA91bFQvhuj842BEGysKo06Qkaw_oTqrh57Aae2nzRw9P5MVf4_Iy99oeW82f_fhw4292rLOjDNkAImON1UxVhkgopVRjdsDe9g5t9KH6j25TXFHTPz5PTjrsZWLvgpWgCd3PfJXx3R";

// See documentation on defining a message payload.
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle("Normal Haircut")
                        .setBody("User: Bhoju")
                        .build())
                .setToken(registrationToken)
                .build();

// Send a message to the device corresponding to the provided
// registration token.
        String response = FirebaseMessaging.getInstance().send(message);
// Response is a message ID string.
        System.out.println("Successfully sent message: " + response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/socket/test")
    public void test() {
        service.test();
    }

    // Message received from barber
    @MessageMapping("/barber/{id}")
    public void greeting(@DestinationVariable Integer id, Map<String, Object> message) {
//        Thread.sleep(1000); // simulated delay
        System.out.println(message.toString());
        System.out.println("MESSAGE FROM topic/test");
        service.sendMessageToCustomer(message, id);
    }

    // Message received from customer
    @MessageMapping("/customer/{id}")
    public void customer(@DestinationVariable Integer id, SocketDto socketDto) {
        System.out.println(socketDto.toString());
        service.sendMessageToNearbyBarbers(id, socketDto);
    }
}
