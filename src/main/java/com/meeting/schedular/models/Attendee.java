package com.meeting.schedular.models;

/**
 *
 */
public class Attendee {
    private final String name;

    public Attendee(final String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "name='" + name + '\'' +
                '}';
    }
}
