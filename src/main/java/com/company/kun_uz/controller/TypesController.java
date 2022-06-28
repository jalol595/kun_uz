package com.company.kun_uz.controller;

import com.company.kun_uz.dto.TypesDTO;
import com.company.kun_uz.enums.LangEnum;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.TypesService;
import com.company.kun_uz.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/types")
@RestController
public class TypesController {

    @Autowired
    private TypesService typesService;

// PUBLIC

    @GetMapping("/public")
    public ResponseEntity<List<TypesDTO>> getArticleList1(@RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang) {
        List<TypesDTO> list = typesService.getList(lang);
        return ResponseEntity.ok().body(list);
    }


// SECURED

    @PostMapping("/adm")
    public ResponseEntity<?> create(@RequestBody TypesDTO typeDto,
                                    HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        TypesDTO responseTypeDTO = typesService.create(typeDto);
        return ResponseEntity.ok().body(responseTypeDTO);
    }


    @GetMapping("/adm")
    public ResponseEntity<List<TypesDTO>> getlist(@RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang,
                                                  HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<TypesDTO> list = typesService.getListOnlyForAdmin(lang);
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/adm/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody TypesDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        TypesDTO typeDTO = typesService.update(id, dto);
        return ResponseEntity.ok().body(typeDTO);
    }

    @DeleteMapping("/adm/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        typesService.delete(id);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }

    @GetMapping("/adm/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "3") int size,
                                                  HttpServletRequest request,
                                                  @RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        PageImpl response = typesService.pagination(page, size, lang);
        return ResponseEntity.ok().body(response);
    }

}
