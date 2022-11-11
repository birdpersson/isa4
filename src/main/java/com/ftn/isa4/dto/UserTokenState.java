package com.ftn.isa4.dto;

import com.ftn.isa4.model.User;

public class UserTokenState {

    private String accessToken;
    private Long expiresIn;
    private User.Role role;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.role = null;
    }

    public UserTokenState(String accessToken, long expiresIn, User.Role role) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }
}
