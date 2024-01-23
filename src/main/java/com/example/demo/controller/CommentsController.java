package com.example.demo.controller;

import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentService commentService;


    // 특정 사용자가 작성한 댓글 삭제
    @DeleteMapping("/{commentId}/users/{userId}")
    public ResponseEntity<Long> deleteComment(
        @PathVariable(name = "commentId") Long commentId, @PathVariable(name = "userId") Long userId) {

        Long deleteCheck = commentService.deleteComment(commentId, userId);

        if (deleteCheck > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 오래된 댓글 삭제
    @DeleteMapping("/pre/{month}")
    public ResponseEntity<Long> deletePreMonthComment(@PathVariable(name = "month") Long month) {

        Long deleteCheck = commentService.deletePreMonthComment(month);

        if (deleteCheck > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
