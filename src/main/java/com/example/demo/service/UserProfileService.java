package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.domain.UserProfile;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostCategoriesMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.UserProfileMapper;
import com.example.demo.model.common.form.OrderByForm;
import com.example.demo.model.userprofile.dto.UserDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostCategoriesMapper postCategoriesMapper;

    @Autowired
    private CommentMapper commentMapper;


    // 다수의 사용자 조회
    public List<UserDto> getCustomUser(OrderByForm orderByForm, Long offset, Long page) {

        List<UserProfile> userList = userProfileMapper.getCustomUser(orderByForm, offset, page);
        return userList.stream().map(UserDto::from).collect(Collectors.toList());
    }


    // 특정 사용자 삭제
    @Transactional
    public Long deleteUser(Long userId) {

        List<Post> postList = postMapper.getUserPost(userId);
        Long deleteCount = 0L;

        if (!postList.isEmpty()) {
            List<Long> postIdList = postList.stream().map(Post::getPostId).toList();

            commentMapper.deleteCommentByPostList(postIdList);
            commentMapper.deleteCommentByUser(userId);

            postCategoriesMapper.deletePostCategoriesByPostList(postIdList);

            postMapper.deletePostList(postIdList);

            deleteCount = userProfileMapper.deleteUser(userId);
        }
        return deleteCount;
    }
}


























