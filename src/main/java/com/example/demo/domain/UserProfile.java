package com.example.demo.domain;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile {

    private Long userId;
    private String biography;
    private String profilePic;
    private Timestamp lastLoginAt;
}
