package com.example.demo.mapper;

import com.example.demo.model.post.form.PostStatusForm;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PostCategoriesMapper {

    // 게시물의 상태에 따른 카테고리 변경
    Long updateCategoryByPostStatus(Long categoryId, PostStatusForm postStatusForm);


    // 게시물에 해단된 게시물-카테고리 삭제 (Long)
    Long deletePostCategoriesByPost(Long postId);

    // 게시물에 해당된 게시물-카테고리 삭제 (List)
    void deletePostCategoriesByPostList(List<Long> postId);
}