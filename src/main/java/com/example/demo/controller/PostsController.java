package com.example.demo.controller;

import com.example.demo.domain.Post;
import com.example.demo.model.comment.dto.CommentDto;
import com.example.demo.model.common.dto.PostCategoryNameDto;
import com.example.demo.model.common.form.ManyStandardId;
import com.example.demo.model.common.form.OrderByForm;
import com.example.demo.model.common.form.StandardDateForm;
import com.example.demo.model.post.dto.PostBestListDto;
import com.example.demo.model.post.dto.PostCommentCountDto;
import com.example.demo.model.post.dto.PostDto;
import com.example.demo.model.post.dto.PostTitleContentDto;
import com.example.demo.model.post.dto.PostTitleContentViewDto;
import com.example.demo.model.post.dto.PostTitleCreateAtDto;
import com.example.demo.model.post.dto.PostTitleViewDto;
import com.example.demo.model.post.form.PostStatusForm;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostCategoriesService;
import com.example.demo.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러가 HTTP 요청을 처리하고, 자바 객체를 HTTP 응답 본문으로 직접 반환하도록 합니다.
/*
 * 1. 기본적인 컨트롤러의 기능 - HTTP 요청을 받아들이는 엔드포인트(endpoints)로 사용
 * 2. 자동 역직렬화(Request Body -> Java Object): 클라이언트로부터 오는 JSON 형식의 HTTP 요청 본문을 자바 객체로 자동으로 변환합니다.
 * 3. 자동 직렬화(Java Object -> Response Body): 자바 객체를 JSON 형식의 HTTP 응답 본문으로 자동 변환하여 클라이언트에게 보냅니다.
 * */
@RequestMapping("/posts") // 컨트롤러의 URI 구성
public class PostsController {

    @Autowired    //의존성 주입
    private PostService postsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostCategoriesService postCategoriesService;


    // 게시물 조회 (GET)
    @GetMapping("/{postId}") //같은 경로를 사용하더라도 http mathod로 오버로딩 가능
    public ResponseEntity<Post> getPost(@PathVariable("postId") Long postId) {

        Post post = postsService.getPostById(postId);

        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 상태에 따른 게시물 조회
    @GetMapping("/status")
    public ResponseEntity<List<PostTitleContentViewDto>> getStatusPost(
        @RequestBody PostStatusForm postStatusForm) {

        List<PostTitleContentViewDto> postList = postsService.getStatusPost(postStatusForm);

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 가장 많이 조회된 상위 5개 게시물 조회
    @GetMapping("/most-views/{limit}")
    public ResponseEntity<List<PostTitleViewDto>> getMostViewPost(
        @PathVariable("limit") Long limit) {

        List<PostTitleViewDto> postList = postsService.getMostViewPost(limit);

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 특정 게시물에 대한 모든 댓글 조회
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentByPost(
        @PathVariable("postId") Long postId) {

        List<CommentDto> postList = commentService.getCommentByPost(postId);

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 각 게시물별 댓글 수 조회
    @GetMapping("/comments/count")
    public ResponseEntity<List<PostCommentCountDto>> getCommentCountByPost() {

        List<PostCommentCountDto> postList = postsService.getCommentCountByPost();

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 특정 기간 동안 작성된 게시물 조회
    @GetMapping("/pre/{month}")
    public ResponseEntity<List<PostTitleCreateAtDto>> getPreMonthPost(
        @PathVariable("month") Long month) {

        List<PostTitleCreateAtDto> postList = postsService.getPreMonthPost(month);

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Best 포스트 조회
    @GetMapping("/best/{limit}")
    public ResponseEntity<PostBestListDto> getBestPost(
        @PathVariable("limit") Long limit) {

        PostBestListDto bestPost = postsService.getBestPost(limit);

        if (bestPost != null) {
            return new ResponseEntity<>(bestPost, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 다수의 게시물 조회
    @PostMapping("/pages")
    public ResponseEntity<List<PostCategoryNameDto>> getCustomPost(
        @RequestBody OrderByForm orderByForm,
        @RequestParam Long offset, @RequestParam Long page) {

        List<PostCategoryNameDto> postList = postsService.getCustomPost(orderByForm, offset, page);

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // 특정 게시물의 조회수 증가
    @PatchMapping("/{postId}/view-count")
    public ResponseEntity<PostDto> updateViewCountPost(@PathVariable("postId") Long postId) {

        PostDto postDto = postsService.updateViewCountPost(postId);

        if (postDto != null) {
            return new ResponseEntity<>(postDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 게시물의 상태에 따른 카테고리 변경
    @PatchMapping("/status/categories/{categoryId}")
    public ResponseEntity<List<PostTitleContentDto>> updateCategoryByPostStatus(
        @PathVariable("categoryId") Long categoryId, @RequestBody PostStatusForm postStatusForm) {

        List<PostTitleContentDto> postList =
            postCategoriesService.updateCategoryByPostStatus(categoryId, postStatusForm);

        if (postList != null) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // 게시물 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Long> deletePost(@PathVariable("postId") Long postId) {

        Long deleteCheck = postsService.deletePost(postId);

        if (deleteCheck > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 특정 상태의 게시물 삭제
    @DeleteMapping("/status")
    public ResponseEntity<Long> deletePostStatus(@RequestBody PostStatusForm postStatusForm) {

        Long deleteCheck = postsService.deletePostStatus(postStatusForm);

        if (deleteCheck > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 조회수가 낮은 게시물 삭제
    @DeleteMapping("/views/{limit}")
    public ResponseEntity<Long> deletePostLowView(@PathVariable("limit") Long limit) {

        Long deleteCheck = postsService.deletePostLowView(limit);

        if (deleteCheck > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 특정 날짜 이전에 작성된 게시물과 그 댓글 삭제
    @DeleteMapping("/before-date")
    public ResponseEntity<Long> deleteBeforeDate(@RequestBody StandardDateForm standardDateForm) {

        Long deleteCheck = postsService.deleteBeforeDate(standardDateForm);

        if (deleteCheck > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 다수의 게시물 삭제
    @DeleteMapping("/many-ids")
    public ResponseEntity<Long> deleteManyId(@RequestBody ManyStandardId manyStandardId) {

        Long deleteCheck = postsService.deleteManyId(manyStandardId);

        if (deleteCheck > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // 게시물 생성 (POST)
    @PostMapping // http method 설정
    public ResponseEntity<Post> createPost(@RequestBody Post post) {

        Post createPost = postsService.createPost(post);

        return new ResponseEntity<>(createPost,HttpStatus.CREATED);
    }


    // 게시물 수정 (PUT)
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(
        @PathVariable(name = "postId") Long postId, @RequestBody Post updatedPost) {
        int postUpdateCount = postsService.updatePost(postId, updatedPost);
        if (postUpdateCount > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}