package com.ftn.isa4.dto;

import java.time.Instant;

public class AppointmentRequest {
    private Instant start;
    private Long duration;

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
