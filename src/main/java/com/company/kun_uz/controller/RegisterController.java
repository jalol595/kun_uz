package com.company.kun_uz.controller;

import com.company.kun_uz.dto.ProfileDto;
import com.company.kun_uz.service.RegisterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/register")
@RestController
public class RegisterController {

    @Autowired
    private RegisterationService registerationService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProfileDto profileDto) {
        ProfileDto dto = registerationService.create(profileDto);
        return ResponseEntity.ok().body(dto);
    }


}
