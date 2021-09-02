package com.meeting.schedular.controllers;

import com.meeting.schedular.exceptions.MeetingRoomNotAvailableException;
import com.meeting.schedular.exceptions.MeetingRoomNotFoundException;
import com.meeting.schedular.models.Attendee;
import com.meeting.schedular.models.Meeting;
import com.meeting.schedular.services.MeetingScheduleService;

import java.util.*;

public class MeetingScheduleController {
    private final MeetingScheduleService meetingScheduleService;

    public MeetingScheduleController(final MeetingScheduleService meetingScheduleService) {
        this.meetingScheduleService = meetingScheduleService;
    }

    public Meeting scheduleMeeting(final String meetingRoomId, final Date startTime, final Date endTime,
                                   final List<Attendee> attendees) throws MeetingRoomNotFoundException, MeetingRoomNotAvailableException {

        return meetingScheduleService.scheduleMeeting(meetingRoomId,startTime,endTime,attendees);

    }

    public List<Meeting> getPastMeetings(){
        return meetingScheduleService.getMeetingsHistory();
    }
}
