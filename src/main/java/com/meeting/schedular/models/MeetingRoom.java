package com.meeting.schedular.models;

import java.util.Date;

public class MeetingRoom {
    private final String id;
    private final MeetingCalendar calendar;

    public MeetingRoom(String id, final MeetingCalendar calendar) {
        this.id = id;
        this.calendar = calendar;
    }

    public MeetingCalendar getCalendar() {
        return calendar;
    }

    public void scheduleMeeting(final Meeting meeting){
         calendar.createNewMeeting(meeting);
    }

    public boolean checkIfAvailable(final Date startTime, final Date endTime){
        return calendar.checkAvailability(startTime,endTime);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "id='" + id + '\'' +
                '}';
    }
}
