package com.ftn.isa4.model;

import org.threeten.extra.Interval;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collection;

@Entity
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private Long average;

    @Column
    private String description;

    @Column
    private LocalTime opens;

    @Column
    private LocalTime closes;

    @OneToMany
    private Collection<Appointment> appointments;

    @OneToMany
    private Collection<User> admins;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAverage() {
        return average;
    }

    public void setAverage(Long average) {
        this.average = average;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getOpens() {
        return opens;
    }

    public void setOpens(LocalTime opens) {
        this.opens = opens;
    }

    public LocalTime getCloses() {
        return closes;
    }

    public void setCloses(LocalTime closes) {
        this.closes = closes;
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public Collection<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Collection<User> admins) {
        this.admins = admins;
    }

    public boolean isWithinWorkHours(Interval interval) {
        LocalTime start = LocalTime.from(interval.getStart().atZone(ZoneId.systemDefault()));
        LocalTime end = LocalTime.from(interval.getEnd().atZone(ZoneId.systemDefault()));

        return start.isAfter(opens) && start.isBefore(closes)
                && end.isAfter(opens) && end.isBefore(closes)
                && end.isAfter(start);
    }
}
