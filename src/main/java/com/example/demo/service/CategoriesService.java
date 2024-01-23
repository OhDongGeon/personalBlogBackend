package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.mapper.CategoriesMapper;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostCategoriesMapper;
import com.example.demo.mapper.PostMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriesService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostCategoriesMapper postCategoriesMapper;

    @Autowired
    private CategoriesMapper categoriesMapper;


    // 특정 사용자 삭제
    @Transactional
    public Long deleteCategory(Long categoryId) {

        List<Post> postList = postMapper.getCategoryPost(categoryId);
        Long deleteCount = 0L;

        if (!postList.isEmpty()) {
            List<Long> postIdList = postList.stream().map(Post::getPostId).toList();

            commentMapper.deleteCommentByPostList(postIdList);

            postCategoriesMapper.deletePostCategoriesByPostList(postIdList);

            postMapper.deletePostList(postIdList);

            deleteCount = categoriesMapper.deleteCategory(categoryId);
        }
        return deleteCount;
    }
}
