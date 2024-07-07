package com.example.hamro_barber.socket;

import com.example.hamro_barber.model.dto.SocketDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class SocketTopics {
    private final SocketUtils socketUtils;

//    public void hello() {
//        SocketDto socketDto = new SocketDto(1L, "Sarthak", "Haricut");
//        socketUtils.invokeWebSocketEndpoint("/topic/receive/4", socketDto);
//    }

    public void test(Map<String, Object> map) {
        socketUtils.invokeWebSocketEndpoint("/topic/test", map);
    }

    public void toBarber(SocketDto socketDto, Integer id) {
        System.out.println("SENDIND TO SOCKET:");
        System.out.println("/topic/barber/" + id);
        System.out.println("Data: " + socketDto);
        socketUtils.invokeWebSocketEndpoint("/topic/barber/" + id, socketDto);
    }

    public void toCustomer(String json, Integer id) {
        socketUtils.invokeWebSocketEndpoint("/topic/customer/" + id, json);
    }
}
