package com.example.demo.controller;

import com.example.demo.model.common.form.OrderByForm;
import com.example.demo.model.post.dto.PostCountByUserDto;
import com.example.demo.model.post.dto.PostTitleContentCreateAtDto;
import com.example.demo.model.post.dto.PostTitleCreateAtDto;
import com.example.demo.model.userprofile.dto.UserDto;
import com.example.demo.service.PostService;
import com.example.demo.service.UserProfileService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserProfilesController {

    @Autowired
    private PostService postsService;

    @Autowired
    private UserProfileService userProfileService;


    // 특정 사용자의 게시물 조회
    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<PostTitleCreateAtDto>> getUserPost(
        @PathVariable("userId") Long userId) {

        List<PostTitleCreateAtDto> postList = postsService.getUserPost(userId);

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 각 사용자별 게시물 수 조회
    @GetMapping("/posts/count")
    public ResponseEntity<List<PostCountByUserDto>> getPostCountByUser() {

        List<PostCountByUserDto> postList = postsService.getPostCountByUser();

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 특정 사용자의 최근 게시물 조회
    @GetMapping("/{userId}/latest/{limit}")
    public ResponseEntity<List<PostTitleContentCreateAtDto>> getUserLatestPost(
        @PathVariable("userId") Long userId, @PathVariable("limit") Long limit) {

        List<PostTitleContentCreateAtDto> postList = postsService.getUserLatestPost(userId, limit);

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 다수의 사용자 조회
    @PostMapping("/info/pages")
    public ResponseEntity<List<UserDto>> getCustomUser(
        @RequestBody OrderByForm orderByForm,
        @RequestParam Long offset, @RequestParam Long page) {

        List<UserDto> postList = userProfileService.getCustomUser(orderByForm, offset, page);

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 사용자 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable("userId") Long userId) {

        Long deleteCheck = userProfileService.deleteUser(userId);

        if (deleteCheck > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
