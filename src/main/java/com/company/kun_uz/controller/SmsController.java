package com.company.kun_uz.controller;

import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.SmsService;
import com.company.kun_uz.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public class SmsController {


    @Autowired
    private SmsService smsService;

    @GetMapping("/adm/pagination")
    public ResponseEntity<PageImpl> pagination(@RequestParam(value = "page" , defaultValue = "1") int page,
                                               @RequestParam(value = "size" ,defaultValue = "5" ) int size,
                                               HttpServletRequest request){
        HttpHeaderUtil.getId(request , ProfileRole.ADMIN);
        PageImpl response = smsService.paginationSms(page , size);
        return ResponseEntity.ok().body(response);
    }


}
