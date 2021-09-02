package com.meeting.schedular.controllers;

import com.meeting.schedular.models.Attendee;
import com.meeting.schedular.services.NotificationService;
import java.util.*;

public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotifications(final List<Attendee> attendees){
        notificationService.sendNotification(attendees);
    }
}
