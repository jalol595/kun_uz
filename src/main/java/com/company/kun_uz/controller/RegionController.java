package com.company.kun_uz.controller;

import com.company.kun_uz.dto.RegionDTO;
import com.company.kun_uz.enums.LangEnum;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.RegionService;
import com.company.kun_uz.util.HttpHeaderUtil;
import com.company.kun_uz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/region")
@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    // PUBLIC

    @GetMapping("")
    public ResponseEntity<List<RegionDTO>> getlistRegion(@RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang) {
        List<RegionDTO> list = regionService.getList(lang);
        return ResponseEntity.ok().body(list);
    }

    // SECURE

    @PostMapping("/adm")
    public ResponseEntity<?> create(@RequestBody RegionDTO regionDto,
                                    HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        regionService.create(regionDto);
        return ResponseEntity.ok().body("SuccsessFully created");
    }


    @GetMapping("/adm")
    public ResponseEntity<List<RegionDTO>> getlist(@RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang,
                                                   HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<RegionDTO> list = regionService.getListOnlyForAdmin(lang);
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/adm/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody RegionDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        RegionDTO regionDTO = regionService.update(id, dto);
        return ResponseEntity.ok().body(regionDTO);
    }

    @DeleteMapping("/adm/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        regionService.delete(id);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }


    @GetMapping("/adm/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "3") int size,
                                                  HttpServletRequest request,
                                                  @RequestHeader(value = "Accept-Language", defaultValue = "ru") LangEnum lang) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        PageImpl response = regionService.pagination(page, size, lang);
        return ResponseEntity.ok().body(response);
    }

}
