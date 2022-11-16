package com.ftn.isa4.controller;

import com.ftn.isa4.model.Center;
import com.ftn.isa4.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/center")
public class CenterController {
    @Autowired
    private CenterService centerService;

    @GetMapping("/")
    public ResponseEntity<Collection<Center>> getCenters() {
        return new ResponseEntity<>(centerService.findAll(), HttpStatus.OK);
    }
}
