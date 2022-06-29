package com.company.kun_uz.controller;

import com.company.kun_uz.dto.AuthDTO;
import com.company.kun_uz.dto.EmailDTO;
import com.company.kun_uz.dto.ProfileDTO;
import com.company.kun_uz.dto.RegistrationDTO;
import com.company.kun_uz.enums.LangEnum;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.AuthService;
import com.company.kun_uz.service.EmailService;
import com.company.kun_uz.service.ProfileService;
import com.company.kun_uz.util.HttpHeaderUtil;
import com.company.kun_uz.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "Authorization and Registeration")
@RestController
@RequestMapping("/auth")
public class AuthController {



    @Autowired
    private AuthService authService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "Registeration", notes = "Method for registiration")
    @PostMapping("/registration")
    public ResponseEntity<?> createByAdmin(@RequestBody @Valid RegistrationDTO dto) {

        log.info("Request for login {}", dto);

        String response = authService.registiration(dto);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Login", notes = "Method for login")
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody @Valid AuthDTO dto) {

        log.info("Request for login {}", dto); ///////////log

        ProfileDTO profileDto = authService.login(dto);
        return ResponseEntity.ok(profileDto);
    }

    @ApiOperation(value = "email verification", notes = "Method for verification")
    @GetMapping("/email/verification/{token}")
    public ResponseEntity<?> login(@PathVariable("token") String token) {

        Integer PID = JwtUtil.decode(token);
        String s= authService.verificationEmail(PID);
        return ResponseEntity.ok(s);
    }

    @ApiOperation(value = "email list", notes = "Method for email list admin")
    @GetMapping("/adm/emailList")
    public ResponseEntity<PageImpl> getList(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "3") int size,
                                                  HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        PageImpl response = emailService.getList(page, size);
        return ResponseEntity.ok().body(response);
    }

}
