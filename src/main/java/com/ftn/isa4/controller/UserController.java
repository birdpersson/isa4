package com.ftn.isa4.controller;

import com.ftn.isa4.dto.UserResponse;
import com.ftn.isa4.model.User;
import com.ftn.isa4.security.TokenUtils;
import com.ftn.isa4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/form")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> create(@RequestBody Collection<String> selected, HttpServletRequest request) {
        User user = userService.findByUsername(tokenUtils.getUsernameFromToken(tokenUtils.getToken(request)));
        user.setQuestionnaire(selected);
        return new ResponseEntity<>(new UserResponse(userService.edit(user)), HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> view(HttpServletRequest request) {
        String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity<>(new UserResponse(userService.findByUsername(username)), HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Collection<UserResponse>> getAll(HttpServletRequest request) {
        Collection<User> users = userService.findAll();
        Collection<UserResponse> response = new ArrayList<>();
        for (User u : users) {
            response.add(new UserResponse(u));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
