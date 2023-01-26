package com.ftn.isa4.dto;

import com.ftn.isa4.model.Appointment;
import com.ftn.isa4.model.Center;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

public class CenterResponse {
    private final Long id;
    private final String name;
    private final String city;
    private final String address;
    private final Long rating;
    private final String description;
    private final LocalTime opens;
    private final LocalTime closes;
    private final Collection<AppointmentResponse> appointments = new ArrayList<>();

    public CenterResponse(Center center) {
        this.id = center.getId();
        this.name = center.getName();
        this.city = center.getCity();
        this.address = center.getAddress();
        this.rating = center.getRating();
        this.description = center.getDescription();
        this.opens = center.getOpens();
        this.closes = center.getCloses();
        for (Appointment a : center.getAppointments()) {
            if (!a.isReserved())
                this.appointments.add(new AppointmentResponse(a));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Long getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getOpens() {
        return opens;
    }

    public LocalTime getCloses() {
        return closes;
    }

    public Collection<AppointmentResponse> getAppointments() {
        return appointments;
    }
}
