package com.company.kun_uz.controller;

import com.company.kun_uz.dto.AttachDTO;
import com.company.kun_uz.dto.ProfileDTO;
import com.company.kun_uz.dto.UpdateAttacheDTO;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.ProfileService;
import com.company.kun_uz.util.HttpHeaderUtil;
import com.company.kun_uz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/profile")
@RestController
public class ProfileController {


    @Autowired
    private ProfileService profileService;


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ProfileDTO profileDto,
                                    HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        ProfileDTO dto = profileService.create(profileDto);
        return ResponseEntity.ok().body(dto);
    }


    @GetMapping("")
    public ResponseEntity<List<ProfileDTO>> getProfileList(HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<ProfileDTO> list = profileService.getList();
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/detail")
    public ResponseEntity<?> update(@RequestBody ProfileDTO dto,
                                    HttpServletRequest request) {
        Integer profileId = HttpHeaderUtil.getId(request);
        profileService.update(profileId, dto);
        return ResponseEntity.ok().body("Sucsessfully updated");
    }


    @PutMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody ProfileDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        profileService.update(id, dto);
        return ResponseEntity.ok().body("Succsessfully updated");
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        profileService.delete(id);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "3") int size,
                                                  HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        PageImpl response = profileService.pagination(page, size);
        return ResponseEntity.ok().body(response);
    }

/*
    @PutMapping("/updatePhoto")
    public ResponseEntity<?> updatePhoto(@RequestBody UpdateAttacheDTO dto,
                                     HttpServletRequest request) {
        Integer pId = HttpHeaderUtil.getId(request, ProfileRole.USER);
        profileService.updatePhoto(dto, pId);
        return ResponseEntity.ok().body("Succsessfully updated");
    }*/

}
