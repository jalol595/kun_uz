package com.company.kun_uz.controller;

import com.company.kun_uz.dto.AuthDto;
import com.company.kun_uz.dto.ProfileDto;
import com.company.kun_uz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ProfileDto> login(@RequestBody AuthDto dto) {
        ProfileDto profileDto = authService.auth(dto);
        return ResponseEntity.ok(profileDto);
    }


}
