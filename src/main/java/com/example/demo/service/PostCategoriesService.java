package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.mapper.PostCategoriesMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.model.post.dto.PostTitleContentDto;
import com.example.demo.model.post.form.PostStatusForm;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCategoriesService {

    @Autowired
    private PostCategoriesMapper postCategoriesMapper;

    @Autowired
    private PostMapper postMapper;


    // 게시물의 상태에 따른 카테고리 변경
    public List<PostTitleContentDto> updateCategoryByPostStatus(
        Long categoryId, PostStatusForm postStatusForm) {

        Long postUpdateCount =
            postCategoriesMapper.updateCategoryByPostStatus(categoryId, postStatusForm);

        if (postUpdateCount >= 0) {
            List<Post> postList = postMapper.getCategoryPost(categoryId);
            return postList.stream().map(PostTitleContentDto::from).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
