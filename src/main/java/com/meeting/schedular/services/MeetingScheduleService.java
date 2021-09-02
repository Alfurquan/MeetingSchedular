package com.meeting.schedular.services;

import com.meeting.schedular.exceptions.MeetingRoomNotAvailableException;
import com.meeting.schedular.exceptions.MeetingRoomNotFoundException;
import com.meeting.schedular.models.Attendee;
import com.meeting.schedular.models.Meeting;
import com.meeting.schedular.models.MeetingRoom;

import java.util.Date;
import java.util.LinkedList;
import java.util.*;

public class MeetingScheduleService {
    private final MeetingRoomService meetingRoomService;
    private final Queue<Meeting> meetingsHistory;
    private final int MAX_MEETINGS_HISTORY_SIZE = 20;

    public MeetingScheduleService(final MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
        meetingsHistory = new LinkedList<>();
    }

    public Meeting scheduleMeeting(final String meetingRoomId, final Date startTime,
                                       final Date endTime, final List<Attendee> attendees) throws MeetingRoomNotFoundException, MeetingRoomNotAvailableException {

        if(!meetingRoomService.checkIfMeetingRoomAvailable(meetingRoomId,startTime,endTime)){
            throw new MeetingRoomNotAvailableException();
        }

        MeetingRoom meetingRoom = meetingRoomService.getMeetingRoom(meetingRoomId);
        final String id = UUID.randomUUID().toString();
        Meeting meeting = new Meeting(id,attendees,startTime,endTime,meetingRoom);
        meetingRoom.scheduleMeeting(meeting);
        addMeetingToHistory(meeting);
        return meeting;
    }

    private void addMeetingToHistory(final Meeting meeting) {
        meetingsHistory.offer(meeting);
        if (meetingsHistory.size() > MAX_MEETINGS_HISTORY_SIZE)
            meetingsHistory.poll();
    }

    public List<Meeting> getMeetingsHistory(){
        return new ArrayList<>(meetingsHistory);
    }

}
