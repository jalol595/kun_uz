package com.company.kun_uz.controller;

import com.company.kun_uz.dto.SavedArticleDTO;
import com.company.kun_uz.dto.TypesDTO;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.SavedArticleService;
import com.company.kun_uz.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/saved_article")
@RestController
public class SavedArticleController {

    @Autowired
    private SavedArticleService savedArticleService;


    @PostMapping("/adm")
    public ResponseEntity<?> create(@RequestBody SavedArticleDTO dto, HttpServletRequest request) {
        Integer profileId = HttpHeaderUtil.getId(request, ProfileRole.USER);
        SavedArticleDTO responseDTO = savedArticleService.create(dto, profileId);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        Integer profileId = HttpHeaderUtil.getId(request, ProfileRole.USER);
        String delete = savedArticleService.delete(id, profileId);
        return ResponseEntity.ok().body(delete);
    }


    @GetMapping("/adm/list")
    public ResponseEntity<?> getlist(HttpServletRequest request) {
        Integer profileId = HttpHeaderUtil.getId(request, ProfileRole.USER);
        List<SavedArticleDTO> list = savedArticleService.getList(profileId);
        return ResponseEntity.ok().body(list);
    }


}
