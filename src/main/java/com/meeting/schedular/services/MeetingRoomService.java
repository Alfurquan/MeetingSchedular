package com.meeting.schedular.services;

import com.meeting.schedular.exceptions.MeetingRoomNotFoundException;
import com.meeting.schedular.models.MeetingCalendar;
import com.meeting.schedular.models.MeetingRoom;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MeetingRoomService {
    private final Map<String, MeetingRoom> meetingRooms;

    public MeetingRoomService() {
        meetingRooms = new HashMap<>();
    }

    public MeetingRoom createMeetingRoom(final MeetingCalendar calendar){
        final String id = UUID.randomUUID().toString();
        MeetingRoom meetingRoom = new MeetingRoom(id,calendar);
        meetingRooms.put(id,meetingRoom);
        return meetingRoom;
    }

    public MeetingRoom getMeetingRoom(final String id) throws MeetingRoomNotFoundException {
        if(!meetingRooms.containsKey(id)){
            throw new MeetingRoomNotFoundException();
        }
        return meetingRooms.get(id);
    }

    public Map<String, MeetingRoom> getMeetingRooms() {
        return meetingRooms;
    }

    public boolean checkIfMeetingRoomAvailable(final String id, final Date startTime, final Date endTime) throws MeetingRoomNotFoundException {
        MeetingRoom meetingRoom = getMeetingRoom(id);
        return meetingRoom.checkIfAvailable(startTime,endTime);
    }
}
