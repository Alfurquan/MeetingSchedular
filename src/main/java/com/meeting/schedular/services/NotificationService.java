package com.meeting.schedular.services;

import com.meeting.schedular.models.Attendee;
import java.util.*;

public interface NotificationService {
    void sendNotification(List<Attendee> attendees);
}
