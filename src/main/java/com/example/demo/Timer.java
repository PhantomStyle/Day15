package com.example.demo;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Timer {
    private final BufferedReader br;
    private final InputStreamReader reader;

    public Timer() {
        reader = new InputStreamReader(System.in);
        br = new BufferedReader(reader);
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Scheduled(fixedDelay = 1000L)
    public void sendMessage() {
        String message = readMessage();
        rabbitTemplate.convertAndSend(LiveRabbitClientApplication.exchangeName, "client.mih",
                LiveRabbitClientApplication.queueName + "-> " + message);
    }

    private String readMessage() {
        try {
            String input = br.readLine();
            if ("bb".equals(input)) {
                System.out.println("Exit!");
                System.exit(0);
            }
            return input;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }

    }
}
