package com.example.demo.model.post.dto;

import com.example.demo.domain.Post;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostTitleContentCreateAtDto {

    private String title;
    private String content;
    private Timestamp createdAt;

    public static PostTitleContentCreateAtDto from(Post post) {
        return PostTitleContentCreateAtDto.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .createdAt(post.getCreatedAt())
            .build();
    }
}
