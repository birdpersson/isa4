package com.ftn.isa4.model;

import javax.persistence.*;
import java.time.Instant;
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
    private Instant start;

    @Column(name = "finish")
    private Instant end;

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

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Collection<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Collection<User> admins) {
        this.admins = admins;
    }
}
