package com.ftn.isa4.dto;

import com.ftn.isa4.model.Appointment;
import com.ftn.isa4.model.User;

import java.util.ArrayList;
import java.util.Collection;

public class UserResponse {
    private final Long id;
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String city;
    private final String country;
    private final String phone;
    private final String jmbg;
    private final String gender;
    private final String occupation;
    private final String employment;
    private Collection<String> questionnaire = new ArrayList<>();
    private final User.Role role;
    private final Collection<ReservationResponse> appointments = new ArrayList<>();
    private final boolean enabled;
    private final String token;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.phone = user.getPhone();
        this.jmbg = user.getJmbg();
        this.gender = user.getGender();
        this.occupation = user.getOccupation();
        this.employment = user.getEmployment();
        this.questionnaire = user.getQuestionnaire();
        this.role = user.getRole();
        for (Appointment a : user.getAppointments()) {
            if (a.isReserved())
                this.appointments.add(new ReservationResponse(a));
        }
        this.enabled = user.isEnabled();
        this.token = user.getToken();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getJmbg() {
        return jmbg;
    }

    public String getGender() {
        return gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getEmployment() {
        return employment;
    }

    public Collection<String> getQuestionnaire() {
        return questionnaire;
    }

    public User.Role getRole() {
        return role;
    }

    public Collection<ReservationResponse> getAppointments() {
        return appointments;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getToken() {
        return token;
    }
}
