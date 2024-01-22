package com.example.demo.model.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCountByUserDto {

    private String userId;
    private String postCount;
}
