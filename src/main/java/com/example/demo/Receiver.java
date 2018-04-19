package com.example.demo;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Receiver {
    public void receiveMessage(String message){
        System.out.println(LocalDate.now() + ": " + message);
    }
}
