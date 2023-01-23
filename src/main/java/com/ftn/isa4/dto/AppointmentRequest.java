package com.ftn.isa4.dto;

import java.time.Instant;

public class AppointmentRequest {
    private Instant start;
    private Instant end;

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }
}
