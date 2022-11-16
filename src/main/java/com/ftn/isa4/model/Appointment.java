package com.ftn.isa4.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

@Entity
public class Appointment {
    public enum Type {AVAILABLE, RESERVED, CANCELED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Type type;

    @Column
    private Instant start;

    @Column(name = "finish")
    private Instant end;

    @Column
    private Integer price;

    @ManyToOne
    private Center center;

    @ManyToOne
    private User patient;

    @OneToMany
    private Collection<User> staff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public Collection<User> getStaff() {
        return staff;
    }

    public void setStaff(Collection<User> staff) {
        this.staff = staff;
    }
}
