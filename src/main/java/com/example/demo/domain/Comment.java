package com.example.demo.domain;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {

    private Long commentId;
    private Long postId;
    private Long userId;
    private Long parentCommentId;
    private String comments;
    private Timestamp createdAt;
}
