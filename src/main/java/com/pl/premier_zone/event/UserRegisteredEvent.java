package com.pl.premier_zone.event;

import com.pl.premier_zone.user.User;

public record UserRegisteredEvent(String email,String ActualUsername) {
//publisher for event 

}
