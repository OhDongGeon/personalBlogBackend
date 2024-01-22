package com.example.demo.model.post.dto;

import com.example.demo.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostTitleViewDto {

    private String title;
    private Long viewCount;

    public static PostTitleViewDto from(Post post) {
        return PostTitleViewDto.builder()
            .title(post.getTitle())
            .viewCount(post.getViewCount())
            .build();
    }
}
