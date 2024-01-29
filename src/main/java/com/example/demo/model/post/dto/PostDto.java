package com.example.demo.model.post.dto;

import com.example.demo.domain.Post;
import java.sql.Timestamp;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDto {

    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private String status;
    private Long viewCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String imageUrl;

    public static PostDto from(Post post) {
        return PostDto.builder()
            .postId(post.getPostId())
            .userId(post.getUserId())
            .title(post.getTitle())
            .content(post.getContent())
            .status(post.getStatus())
            .viewCount(post.getViewCount())
            .createdAt(post.getCreatedAt())
            .updatedAt(post.getUpdatedAt())
            .imageUrl(post.getImageUrl())
            .build();
    }
}
