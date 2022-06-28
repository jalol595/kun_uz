package com.company.kun_uz.controller;

import com.company.kun_uz.dto.*;
import com.company.kun_uz.dto.article.ArticleDTO;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.CommentLikeService;
import com.company.kun_uz.service.CommentService;
import com.company.kun_uz.service.CommetFilterService;
import com.company.kun_uz.util.HttpHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentLikeService commentLikeService;

    @Autowired
    private CommetFilterService commetFilterService;

    @PostMapping("/user")
    public ResponseEntity<CommentDTO> create(@RequestBody CommentDTO dto,
                                             HttpServletRequest request) {
        Integer profileId = HttpHeaderUtil.getId(request);
        CommentDTO response = commentService.create(dto, profileId);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/adm")
    public ResponseEntity<List<CommentDTO>> getlist( HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<CommentDTO> list = commentService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/user/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody CommentDTO dto,
                                     HttpServletRequest request) {
        Integer pId = HttpHeaderUtil.getId(request);
        commentService.update(id, pId, dto);
        return ResponseEntity.ok().body("Succsessfully updated");
    }

    @DeleteMapping("/adm/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer comId,
                                     HttpServletRequest request) {
        Integer pId = HttpHeaderUtil.getId(request);
        commentService.delete(pId, comId);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<CommentProfileDTO>> getArticleCommentListByArticleId(@PathVariable("id") String article_id) {
        List<CommentProfileDTO> list = commentService.getArticleCommentListByArticleId(article_id);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/adm/list")
    public ResponseEntity<List<CommentProfileArticleDTO>> commentListPagination( HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<CommentProfileArticleDTO> list = commentService.commentListPagination();
        return ResponseEntity.ok().body(list);
    }


    @PostMapping("/filter")
    public ResponseEntity<List<CommentResponseDTO>> filter(@RequestBody @Valid CommentFilterDTO dto,
                                                   HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.PUBLISHER);
        List<CommentResponseDTO>  response = commetFilterService.filter(dto);
        return ResponseEntity.ok().body(response);
    }

}
