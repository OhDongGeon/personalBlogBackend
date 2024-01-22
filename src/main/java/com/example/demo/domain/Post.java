package com.example.demo.domain;

import java.sql.Timestamp;
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
}
