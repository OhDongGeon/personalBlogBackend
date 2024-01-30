package com.example.demo.model.post.dto;

import com.example.demo.model.common.dto.PostCategoryNameDto;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostBestListDto {

    private List<PostCategoryNameDto> mostCommentPosts;
    private List<PostCategoryNameDto> mostViewPosts;
    private Set<PostCategoryNameDto> bestPosts;

    public static PostBestListDto of(
        List<PostCategoryNameDto> mostCommentPosts,
        List<PostCategoryNameDto> mostViewPosts,
        Set<PostCategoryNameDto> bestPosts) {

        return PostBestListDto.builder()
            .mostCommentPosts(mostCommentPosts)
            .mostViewPosts(mostViewPosts)
            .bestPosts(bestPosts)
            .build();
    }
}
