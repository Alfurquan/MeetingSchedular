package com.meeting.schedular;

import com.meeting.schedular.controllers.MeetingRoomController;
import com.meeting.schedular.controllers.MeetingScheduleController;
import com.meeting.schedular.controllers.NotificationController;
import com.meeting.schedular.exceptions.MeetingRoomNotAvailableException;
import com.meeting.schedular.exceptions.MeetingRoomNotFoundException;
import com.meeting.schedular.models.Attendee;
import com.meeting.schedular.models.Meeting;
import com.meeting.schedular.models.MeetingCalendar;
import com.meeting.schedular.services.EmailNotificationService;
import com.meeting.schedular.services.MeetingRoomService;
import com.meeting.schedular.services.MeetingScheduleService;
import com.meeting.schedular.services.NotificationService;

import java.util.*;
import java.util.Calendar;

public class Driver {

    private MeetingRoomController meetingRoomController;
    private MeetingScheduleController meetingScheduleController;
    private MeetingRoomService meetingRoomService;
    private MeetingScheduleService meetingScheduleService;
    private NotificationController notificationController;
    private final List<String> meetingRoomIds;
    private final int NO_OF_MEETING_ROOMS = 5;
    private Calendar calendar;
    public Driver() {
        calendar = Calendar.getInstance();
        meetingRoomIds = new ArrayList<>();
    }

    public void runApp(){
        setupControllersAndServices();
        createMeetingRooms();

        Attendee attendee1 = new Attendee("Rahul");
        Attendee attendee2 = new Attendee("Ram");
        Attendee attendee3 = new Attendee("Anil");
        Attendee attendee4 = new Attendee("Mohan");
        Attendee attendee5 = new Attendee("Khan");

        List<Attendee> attendeeList = new ArrayList<>();
        attendeeList.add(attendee1);
        attendeeList.add(attendee2);
        attendeeList.add(attendee3);

        //try scheduling a meeting
        Date startTime = calendar.getTime();
        calendar.add(Calendar.HOUR_OF_DAY,2);
        Date endTime = calendar.getTime();
        scheduleMeeting(meetingRoomIds.get(0),startTime,endTime,attendeeList);

        attendeeList = new ArrayList<>();
        attendeeList.add(attendee4);
        attendeeList.add(attendee5);

        calendar.add(Calendar.HOUR_OF_DAY, -1);
        startTime = calendar.getTime();
        calendar.add(Calendar.HOUR_OF_DAY,3);
        endTime = calendar.getTime();
        scheduleMeeting(meetingRoomIds.get(0),startTime,endTime,attendeeList);

        System.out.println("List of past meetings");

        List<Meeting> meetings = meetingScheduleController.getPastMeetings();
        for (Meeting meeting : meetings)
            System.out.println(meeting);
    }

    private void scheduleMeeting(final String meetingRoomId, final Date startTime,
                                 final Date endTime, final List<Attendee> attendees){

        try {
            Meeting meeting = meetingScheduleController.scheduleMeeting(meetingRoomId,startTime,endTime,attendees);
            System.out.println("Meeting successfully scheduled..");
            System.out.println(meeting);
            notificationController.sendNotifications(attendees);
        }catch (MeetingRoomNotFoundException e){
            System.out.println("Meeting room with that id does not exists");
        }catch (MeetingRoomNotAvailableException e){
            System.out.println("Meeting room is occupied for the requested time interval");
        }
    }

    private void createMeetingRooms() {
        for(int i = 0; i < NO_OF_MEETING_ROOMS; i++){
            String id = meetingRoomController.createMeetingRoom(new MeetingCalendar());
            meetingRoomIds.add(id);
        }
    }

    private void setupControllersAndServices() {
        meetingRoomService = new MeetingRoomService();
        meetingScheduleService = new MeetingScheduleService(meetingRoomService);
        meetingRoomController = new MeetingRoomController(meetingRoomService);
        meetingScheduleController = new MeetingScheduleController(meetingScheduleService);
        notificationController = new NotificationController(new EmailNotificationService());
    }
}
