package com.example.demo.model.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCommentCountDto {

    private Long postId;
    private String title;
    private Long commentCount;
}
