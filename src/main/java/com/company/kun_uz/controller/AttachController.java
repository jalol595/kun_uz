package com.company.kun_uz.controller;

import com.company.kun_uz.dto.AttachDTO;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.AttachService;
import com.company.kun_uz.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RequestMapping("/attache")
@RestController
public class AttachController {

    @Autowired
    private AttachService attachService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        AttachDTO attachDTO = attachService.saveToSystem(file);
        return ResponseEntity.ok().body(attachDTO);
    }



    @GetMapping(value = "/open_general/{id}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("id") String id) {
        return attachService.open_general(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable("id") String id) {
        Resource file = attachService.download(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> upload(@PathVariable("id") String id) {
        String response = attachService.delete(id);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        if (fileName != null && fileName.length() > 0) {
            try {
                return this.attachService.loadImage(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
        return null;
    }

    @GetMapping("/adm/pagination")
    public ResponseEntity<PageImpl> pagination(@RequestParam(value = "page" , defaultValue = "1") int page,
                                               @RequestParam(value = "size" ,defaultValue = "5" ) int size,
                                               HttpServletRequest request){
        HttpHeaderUtil.getId(request , ProfileRole.ADMIN);
        PageImpl response = attachService.paginationAttach(page , size);
        return ResponseEntity.ok().body(response);
    }

}
