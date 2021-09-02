package com.meeting.schedular.controllers;

import com.meeting.schedular.models.MeetingCalendar;
import com.meeting.schedular.services.MeetingRoomService;

public class MeetingRoomController {
    private final MeetingRoomService meetingRoomService;

    public MeetingRoomController(final MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }

    public String createMeetingRoom(final MeetingCalendar calendar){
        return meetingRoomService.createMeetingRoom(calendar).getId();
    }
}
