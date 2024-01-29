package com.example.demo.model.post.dto;

import com.example.demo.domain.Post;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostBestListDto {

    private List<Post> mostCommentPosts;
    private List<Post> mostViewPosts;
    private Set<Post> bestPosts;

    public static PostBestListDto of (
        List<Post> mostCommentPosts, List<Post> mostViewPosts, Set<Post> bestPosts) {

        return PostBestListDto.builder()
            .mostCommentPosts(mostCommentPosts)
            .mostViewPosts(mostViewPosts)
            .bestPosts(bestPosts)
            .build();
    }
}
