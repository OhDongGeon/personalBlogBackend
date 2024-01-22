package com.example.demo.model.post.dto;

import com.example.demo.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostTitleContentDto {

    private String title;
    private String content;

    public static PostTitleContentDto from(Post post) {
        return PostTitleContentDto.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .build();
    }
}
