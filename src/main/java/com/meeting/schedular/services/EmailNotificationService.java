package com.meeting.schedular.services;

import com.meeting.schedular.models.Attendee;

import java.util.List;

public class EmailNotificationService implements NotificationService {

    @Override
    public void sendNotification(List<Attendee> attendees) {
        for (Attendee attendee : attendees){
            System.out.println("Email notification sent to : " + attendee.getName());
        }
    }
}
