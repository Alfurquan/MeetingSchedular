package com.meeting.schedular.models;

import java.util.*;

public class MeetingCalendar {
    private final List<Meeting> meetings;

    public MeetingCalendar() {
        meetings = new ArrayList<>();
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void createNewMeeting(final Meeting meeting) {
        meetings.add(meeting);
    }

    public boolean checkAvailability(final Date startTime, final Date endTime) {
        //loop through all meetings in calendar and check
        for(Meeting meeting : meetings){
            if(meeting.getEndTime().compareTo(startTime) > 0 || meeting.getStartTime().compareTo(endTime) < 0)
                return false;
        }
        return true;
    }

   /* @Override
    public String toString() {
        return "Calendar{" +
                "meetings=" + meetings +
                '}';
    }*/
}
