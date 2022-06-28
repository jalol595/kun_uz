package com.company.kun_uz.controller;


import com.company.kun_uz.dto.ArticleFilterDTO;
import com.company.kun_uz.dto.TypeAndRegionDTO;
import com.company.kun_uz.dto.article.*;
import com.company.kun_uz.enums.LangEnum;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.service.ArticleService;
import com.company.kun_uz.service.ArticleFilterService;
import com.company.kun_uz.util.HttpHeaderUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleFilterService filterService;


    @PostMapping("/adm")
    public ResponseEntity<ArticleDTO> create(@RequestBody @Valid ArticleCreateDTO dto,
                                             HttpServletRequest request) {
        Integer profileId = HttpHeaderUtil.getId(request, ProfileRole.MODERATOR);
        ArticleDTO response = articleService.create(dto, profileId);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/adm/{id}")
    private ResponseEntity<?> update(@PathVariable("id") String id,
                                     @RequestBody ArticleUpdateDTO dto,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.MODERATOR);
        String update = articleService.update(id, dto);
        return ResponseEntity.ok().body(update);
    }


    @GetMapping("/adm")
    public ResponseEntity<List<ArticleDTO>> getlist(HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<ArticleDTO> list = articleService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }


    @DeleteMapping("/adm/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") String id,
                                     HttpServletRequest request) {
        HttpHeaderUtil.getId(request, ProfileRole.MODERATOR);
        articleService.delete(id);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }

    @PutMapping("/adm/publish/{id}")
    private ResponseEntity<?> changeStatusPublishedDate(@PathVariable("id") String id,
                                                        HttpServletRequest request) {
        Integer moderId = HttpHeaderUtil.getId(request, ProfileRole.PUBLISHER);
        articleService.updateByStatusPulish(id, moderId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/adm/notPublish/{id}")
    private ResponseEntity<?> changeStatusNotPublishedDate(@PathVariable("id") String id,
                                                           HttpServletRequest request) {
        Integer moderId = HttpHeaderUtil.getId(request, ProfileRole.PUBLISHER);
        articleService.updateByStatusNotPulish(id, moderId);
        return ResponseEntity.ok().build();
    }

    //5
    @GetMapping("/typeLast5/{articleTypeKey}")
    public ResponseEntity<List<ArticleDTO>> getLast5ArticleList(@PathVariable("articleTypeKey")
                                                                        String articleTypeKey) {
        List<ArticleDTO> list = articleService.getLast5ArticleListByArticleTypeKey(articleTypeKey);
        return ResponseEntity.ok().body(list);
    }

    //6
    @GetMapping("/typeLast3/{articleTypeKey}")
    public ResponseEntity<List<ArticleDTO>> getLast3ArticlePulishList(@PathVariable("articleTypeKey")
                                                                              String articleTypeKey) {
        List<ArticleDTO> list = articleService.getLast3ArticlePulishTypeKey(articleTypeKey);
        return ResponseEntity.ok().body(list);
    }

    //7
    @GetMapping("/last8List")
    public ResponseEntity<List<ArticleDTO>> getLast8NotIn(@RequestBody ArticleRequestDTO dto) {
        List<ArticleDTO> response = articleService.getLat8ArticleNotIn(dto.getIdList());
        return ResponseEntity.ok().body(response);
    }

    //8
    @GetMapping("/getByIdFull/{id}")
    public ResponseEntity<ArticleDTO> getByIdFull(@PathVariable("id") String id,
                                                  @RequestHeader(value = "Accept-Language", defaultValue = "ru") LangEnum lang) {
        ArticleDTO response = articleService.getByIdFull(id, lang);
        return ResponseEntity.ok().body(response);
    }

    //9
    @GetMapping("/gatFourNoTIn/{articleTypeKey}")
    public ResponseEntity<List<ArticleDTO>> gatMostFour(@PathVariable("articleTypeKey")
                                                                String articleTypeKey,
                                                        @RequestBody ArticleRequestDTO dto) {
        List<ArticleDTO> response = articleService.gatMostFourNotIn(articleTypeKey, dto.getId());
        return ResponseEntity.ok().body(response);
    }

    //10
    @GetMapping("/getMost4ViewedArticleList")
    public ResponseEntity<?> getMost4ViewedArticleList() {
        List<ArticleDTO> articleDTOS = articleService.findMost4ViewedArticleList();
        return ResponseEntity.ok().body(articleDTOS);
    }

    //11
    @GetMapping("/getLast4TagName/{tagName}")
    public ResponseEntity<List<ArticleDTO>> getLast4TagName(@PathVariable("tagName") String tagName) {
        List<ArticleDTO> list = articleService.getTag(tagName);
        return ResponseEntity.ok().body(list);
    }

    //12
    @GetMapping("/getLast5ArticleByTypesAndByRegionKey")
    public ResponseEntity<List<ArticleDTO>> getLast5ArticleByTypesAndByRegion(@RequestBody TypeAndRegionDTO dto) {
        List<ArticleDTO> list = articleService.getLast5ArticleByTypesAndByRegion(dto);
        return ResponseEntity.ok().body(list);
    }

    //13
    @GetMapping("/getLastArticleListRegionKey/{regionKey}")
    public ResponseEntity<List<ArticleDTO>> getLastArticleListRegionKey(@PathVariable("regionKey")
                                                                                String regionKey) {
        List<ArticleDTO> list = articleService.getRegionKey(regionKey);
        return ResponseEntity.ok().body(list);
    }

    //14
    @GetMapping("/getLast5CategoryList/{categoryKey}")
    public ResponseEntity<List<ArticleDTO>> getLast5CategoryList(@PathVariable("categoryKey")
                                                                         String articleTypeKey) {
        List<ArticleDTO> list = articleService.getLast5CategoryList(articleTypeKey);
        return ResponseEntity.ok().body(list);
    }

    //15
    @GetMapping("/getLast5CategoryPagination/{categoryKey}")
    public ResponseEntity<List<ArticleDTO>> getLast5CategoryPagination(@PathVariable("categoryKey")
                                                                               String articleTypeKey) {
        List<ArticleDTO> list = articleService.getLast5CategoryPagination(articleTypeKey);
        return ResponseEntity.ok().body(list);
    }

    /*    19. Increase Article View Count by Article Id
            (article_id)*/
    @GetMapping("/increaseArticleViewCountbyArticleId/{article_id}")
    public ResponseEntity<?> increaseArticleViewCountbyArticleId(@PathVariable("article_id")
                                                                         String article_id) {
        articleService.increaseArticleViewCountbyArticleId(article_id);
        return ResponseEntity.ok().build();
    }



    @PostMapping("/filter")
    public ResponseEntity<List<ArticleDTO>> filter(@RequestBody @Valid ArticleFilterDTO dto,
                                             HttpServletRequest request) {
        List<ArticleDTO>  response = filterService.filter(dto);
        return ResponseEntity.ok().body(response);
    }
}
