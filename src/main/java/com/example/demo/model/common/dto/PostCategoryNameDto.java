package com.example.demo.model.common.dto;

import java.sql.Timestamp;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCategoryNameDto {

    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private String status;
    private Long viewCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String imageUrl;
    private String categoryName;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostCategoryNameDto that = (PostCategoryNameDto) o;
        return Objects.equals(postId, that.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }
}
