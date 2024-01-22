package com.example.demo.model.comment.dto;

import com.example.demo.domain.Comment;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDto {

    private Long commentId;
    private Long postId;
    private Long userId;
    private Long parentCommentId;
    private String comments;
    private Timestamp createdAt;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
            .commentId(comment.getCommentId())
            .postId(comment.getPostId())
            .userId(comment.getUserId())
            .parentCommentId(comment.getCommentId())
            .comments(comment.getComments())
            .createdAt(comment.getCreatedAt())
            .build();
    }
}
