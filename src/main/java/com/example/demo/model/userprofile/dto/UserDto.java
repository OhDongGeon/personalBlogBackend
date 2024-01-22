package com.example.demo.model.userprofile.dto;

import com.example.demo.domain.UserProfile;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

    private Long userId;
    private String biography;
    private String profilePic;
    private Timestamp lastLoginAt;

    public static UserDto from(UserProfile userProfile) {
        return UserDto.builder()
            .userId(userProfile.getUserId())
            .biography(userProfile.getBiography())
            .profilePic(userProfile.getProfilePic())
            .lastLoginAt(userProfile.getLastLoginAt())
            .build();
    }
}
