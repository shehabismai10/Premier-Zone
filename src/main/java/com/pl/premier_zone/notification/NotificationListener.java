package com.pl.premier_zone.notification;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.pl.premier_zone.event.UserRegisteredEvent;

@Component
public class NotificationListener {
    @EventListener
    @Async //so it can work in the background and do not disturb the main shit
    public void handleUserRegistration(UserRegisteredEvent event){
        //this will be executed after the user registered
        System.out.println("🚀 [EVENT DETECTED]: User registered with email: " + event.email());
        System.out.println("🔥 [FINAL TEST]: Real Name is -> " + event.ActualUsername());

    }

}
