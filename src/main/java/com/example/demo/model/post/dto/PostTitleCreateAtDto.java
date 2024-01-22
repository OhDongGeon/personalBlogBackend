package com.example.demo.model.post.dto;

import com.example.demo.domain.Post;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostTitleCreateAtDto {

    private String title;
    private Timestamp createdAt;

    public static PostTitleCreateAtDto from(Post post) {
        return PostTitleCreateAtDto.builder()
            .title(post.getTitle())
            .createdAt(post.getCreatedAt())
            .build();
    }
}
