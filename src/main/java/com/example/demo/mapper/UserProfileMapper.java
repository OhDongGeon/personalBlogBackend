package com.example.demo.mapper;

import com.example.demo.domain.UserProfile;
import com.example.demo.model.common.form.OrderByForm;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserProfileMapper {

    // 다수의 사용자 조회
    List<UserProfile> getCustomUser(OrderByForm orderByForm, Long offset, Long page);


    // 특정 사용자 삭제
    Long deleteUser(Long userId);
}
