package com.example.demo.mapper;

import com.example.demo.model.post.form.PostStatusForm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PostCategoriesMapper {

    // 게시물의 상태에 따른 카테고리 변경
    Long updateCategoryByPostStatus(Long categoryId, PostStatusForm postStatusForm);






    int deletePostCategoriesByPost(Long postId);
}