package com.company.kun_uz.controller;

import com.company.kun_uz.dto.CategoryDTO;
import com.company.kun_uz.dto.JwtDTO;
import com.company.kun_uz.dto.article.ArticleLikeDTO;
import com.company.kun_uz.enums.LangEnum;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.CategoryService;
import com.company.kun_uz.util.HttpHeaderUtil;
import com.company.kun_uz.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
@Slf4j
@Api(tags = "Category")
@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    // PUBLIC

    @ApiOperation(value = "Category List", notes = "Method for get category list")
    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getlistCategory(@RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang) {
        List<CategoryDTO> list = categoryService.getList(lang);
        return ResponseEntity.ok().body(list);
    }


    // SECURED
    @ApiOperation(value = "Category", notes = "Method for create category only admin")
    @PostMapping("/adm")
    public ResponseEntity<String> create(@RequestBody @Valid CategoryDTO categoryDTO,
                                    HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        categoryService.create(categoryDTO);
        return ResponseEntity.ok().body("SuccsessFully created");
    }

    @ApiOperation(value = "Category List only admin", notes = "Method for get category list only admin")
    @GetMapping("/adm")
    public ResponseEntity<List<CategoryDTO>> getlist(@RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang,
                                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<CategoryDTO> list = categoryService.getListOnlyForAdmin(lang);
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Category List only admin", notes = "Method for get category list only admin")
    @PutMapping("/adm/{id}")
    private ResponseEntity<CategoryDTO> update(@PathVariable("id") Integer id,
                                     @RequestBody @Valid CategoryDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        CategoryDTO categoryDTO = categoryService.update(id, dto);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @ApiOperation(value = "Category delete", notes = "Method for delete category  only admin")
    @DeleteMapping("/adm/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        categoryService.delete(id);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }


}
