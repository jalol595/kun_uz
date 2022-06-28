package com.company.kun_uz.controller;

import com.company.kun_uz.dto.CategoryDTO;
import com.company.kun_uz.dto.JwtDTO;
import com.company.kun_uz.dto.article.ArticleLikeDTO;
import com.company.kun_uz.enums.LangEnum;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.CategoryService;
import com.company.kun_uz.util.HttpHeaderUtil;
import com.company.kun_uz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    // PUBLIC

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getlistCategory(@RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang) {
        List<CategoryDTO> list = categoryService.getList(lang);
        return ResponseEntity.ok().body(list);
    }


    // SECURED
    @PostMapping("/adm")
    public ResponseEntity<?> create(@RequestBody CategoryDTO categoryDTO,
                                    HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        categoryService.create(categoryDTO);
        return ResponseEntity.ok().body("SuccsessFully created");
    }


    @GetMapping("/adm")
    public ResponseEntity<List<CategoryDTO>> getlist(@RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang,
                                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<CategoryDTO> list = categoryService.getListOnlyForAdmin(lang);
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/adm/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody CategoryDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        CategoryDTO categoryDTO = categoryService.update(id, dto);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @DeleteMapping("/adm/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        categoryService.delete(id);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }


}
