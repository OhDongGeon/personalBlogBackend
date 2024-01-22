package com.example.demo.service;

import com.example.demo.domain.UserProfile;
import com.example.demo.mapper.UserProfileMapper;
import com.example.demo.model.common.form.OrderByForm;
import com.example.demo.model.userprofile.dto.UserDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileMapper userProfileMapper;


    public List<UserDto> getCustomUser(OrderByForm orderByForm, Long offset, Long page) {

        List<UserProfile> userList = userProfileMapper.getCustomUser(orderByForm, offset, page);
        return userList.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
