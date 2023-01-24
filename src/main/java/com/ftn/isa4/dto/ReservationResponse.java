package com.ftn.isa4.dto;

import com.ftn.isa4.model.Appointment;

public class ReservationResponse {
    private final Long id;
    private final Long centerId;
    private final Long patientId;
    private final String username;
    private final String interval;
    private final String type;

    public ReservationResponse(Appointment appointment) {
        this.id = appointment.getId();
        this.centerId = appointment.getCenter().getId();
        this.patientId = appointment.getPatient().getId();
        this.username = appointment.getPatient().getUsername();
        this.interval = appointment.getInterval().toString();
        this.type = appointment.getType().toString();
    }

    public Long getId() {
        return id;
    }

    public Long getCenterId() {
        return centerId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getUsername() {
        return username;
    }

    public String getInterval() {
        return interval;
    }

    public String getType() {
        return type;
    }
}
