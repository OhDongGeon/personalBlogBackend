package com.example.demo.model.post.dto;

import com.example.demo.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostTitleContentViewDto {

    private String title;
    private String content;
    private Long viewCount;

    public static PostTitleContentViewDto from(Post post) {
        return PostTitleContentViewDto.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .viewCount(post.getViewCount())
            .build();
    }
}
