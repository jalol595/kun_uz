package com.company.kun_uz.controller;

import com.company.kun_uz.dto.ProfileDto;
import com.company.kun_uz.dto.RegionDto;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.RegionService;
import com.company.kun_uz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/region")
@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RegionDto regionDto, @RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, ProfileRole.ADMIN);
        regionService.create(regionDto);
        return ResponseEntity.ok().body("SuccsessFully created");
    }

    @GetMapping("/list")
    public ResponseEntity<List<RegionDto>> getlistRegion(@RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, ProfileRole.ADMIN);
        List<RegionDto> list = regionService.getList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/list")
    public ResponseEntity<List<RegionDto>> getlist(@RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, ProfileRole.ADMIN);
        List<RegionDto> list = regionService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/update/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody RegionDto dto,
                                     @RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, ProfileRole.ADMIN);
        regionService.update(id, dto);
        return ResponseEntity.ok().body("Succsessfully updated");
    }

    @PutMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                     @RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, ProfileRole.ADMIN);
        regionService.delete(id);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }



}
