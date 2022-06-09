package com.company.kun_uz.controller;

import com.company.kun_uz.dto.AuthDto;
import com.company.kun_uz.dto.ProfileDto;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.AuthService;
import com.company.kun_uz.service.ProfileService;
import com.company.kun_uz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<?> createByAdmin(@RequestBody ProfileDto profileDto, @RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, ProfileRole.ADMIN);
        ProfileDto dto = profileService.create(profileDto);
        return ResponseEntity.ok().body(dto);
    }


    @PostMapping("/login")
    public ResponseEntity<ProfileDto> login(@RequestBody AuthDto dto) {
        ProfileDto profileDto = authService.auth(dto);
        return ResponseEntity.ok(profileDto);
    }


}
