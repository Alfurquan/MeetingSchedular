package com.meeting.schedular.models;

import java.util.*;

public class Meeting {
    private final String id;
    private final List<Attendee> attendees;
    private final Date startTime;
    private final Date endTime;
    private final MeetingRoom meetingRoom;


    public Meeting(final String id, final List<Attendee> attendees, final Date startTime, final Date endTime, final MeetingRoom meetingRoom) {
        this.id = id;
        this.attendees = attendees;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingRoom = meetingRoom;
    }

    public String getId() {
        return id;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id='" + id + '\'' +
                ", attendees=" + attendees +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", meetingRoom=" + meetingRoom +
                '}';
    }
}
