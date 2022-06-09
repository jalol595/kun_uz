package com.company.kun_uz.controller;

import com.company.kun_uz.dto.ProfileDto;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.ProfileService;
import com.company.kun_uz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/profile")
@RestController
public class ProfileController {


    @Autowired
    private ProfileService profileService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProfileDto profileDto) {
        ProfileDto dto = profileService.create(profileDto);
        return ResponseEntity.ok().body(dto);
    }

    //by admin super_moderator, maderator
    @GetMapping("/list")
    public ResponseEntity<List<ProfileDto>> getlistBook(@RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, ProfileRole.ADMIN);
        List<ProfileDto> list = profileService.getList();
        return ResponseEntity.ok().body(list);
    }

    //user
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProfileDto dto,
                                             @RequestHeader("Authorization") String jwt) {
        Integer decode = JwtUtil.decode(jwt);
        profileService.update(decode, dto);
        return ResponseEntity.ok().body("Sucsessfully updated");
    }

    //admin
    @PutMapping("/update/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ProfileDto dto, @RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, ProfileRole.ADMIN);
        profileService.update(id, dto);
        return ResponseEntity.ok().body("Succsessfully updated");
    }

    @PutMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id, @RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, ProfileRole.ADMIN);
        profileService.delete(id);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }



}
