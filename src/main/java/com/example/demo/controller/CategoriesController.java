package com.example.demo.controller;

import com.example.demo.model.post.dto.PostTitleContentDto;
import com.example.demo.model.post.dto.PostTitleViewDto;
import com.example.demo.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private PostService postsService;


    // 4. 특정 카테고리에 속하는 게시물 조회
    @GetMapping("/{categoryId}/posts")
    public ResponseEntity<List<PostTitleContentDto>> getCategoryPost(
        @PathVariable("categoryId") Long categoryId) {

        List<PostTitleContentDto> postList = postsService.getCategoryPost(categoryId);

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 9. 특정 카테고리의 게시물 중 가장 많이 조회된 게시물
    @GetMapping("/{categoryId}/posts/most-views/{limit}")
    public ResponseEntity<List<PostTitleViewDto>> getCategoryMostViewPost(
        @PathVariable("categoryId") Long categoryId, @PathVariable("limit") Long limit) {

        List<PostTitleViewDto> postList = postsService.getCategoryMostViewPost(categoryId, limit);

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
