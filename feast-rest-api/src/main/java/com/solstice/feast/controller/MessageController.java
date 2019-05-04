package com.solstice.feast.controller;

import com.solstice.feast.domain.Message;
import com.solstice.feast.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Value("${twilio.number}")
    private String TWILIO_NUMBER;

    @GetMapping("/demo")
    public String demo() {

        Message message = Message.builder()
                .from(TWILIO_NUMBER)
                .to("+16309016008")
                .message("Truck is near you! Go to http://google.com")
                .build();
        return messageService.sendSMS(message).getSid();
    }

    @GetMapping("/alert")
    public String alert(@RequestParam("phone") String toPhone, @RequestParam("body") String body) {

        Message message = Message.builder()
                .from(TWILIO_NUMBER)
                .to(toPhone)
                .message(body)
                .build();
        return messageService.sendSMS(message).getSid();
    }
}
