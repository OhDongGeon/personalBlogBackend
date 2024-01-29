package com.example.demo.domain;

import java.sql.Timestamp;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private String status;
    private Long viewCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postId, post.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }
}
