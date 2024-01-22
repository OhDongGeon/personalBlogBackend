package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostCategoriesMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.model.common.form.OrderByForm;
import com.example.demo.model.post.dto.PostCommentCountDto;
import com.example.demo.model.post.dto.PostCountByUserDto;
import com.example.demo.model.post.dto.PostDto;
import com.example.demo.model.post.dto.PostTitleContentCreateAtDto;
import com.example.demo.model.post.dto.PostTitleContentDto;
import com.example.demo.model.post.dto.PostTitleContentViewDto;
import com.example.demo.model.post.dto.PostTitleCreateAtDto;
import com.example.demo.model.post.dto.PostTitleViewDto;
import com.example.demo.model.post.form.PostStatusForm;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostCategoriesMapper postCategoriesMapper;


    // 게시물 조회
    public Post getPostById(Long postId) {
        return postMapper.getPostById(postId);
    }

    // 모든 게시물 조회 메서드
    public List<Post> getAllPosts() {
        return postMapper.getAllPosts();
    }

    // 공개된 모든 게시물 조회
    public List<PostTitleContentViewDto> getStatusPublicPost() {

        List<Post> postList = postMapper.getStatusPublicPost();
        return postList.stream().map(PostTitleContentViewDto::from).collect(Collectors.toList());
    }

    public List<PostTitleCreateAtDto> getUserPost(Long userId) {

        List<Post> postList = postMapper.getUserPost(userId);
        return postList.stream().map(PostTitleCreateAtDto::from).collect(Collectors.toList());
    }

    // 가장 많이 조회된 상위 5개 게시물 조회
    public List<PostTitleViewDto> getMostViewPost(Long limit) {

        List<Post> postList = postMapper.getMostViewPost(limit);
        return postList.stream().map(PostTitleViewDto::from).collect(Collectors.toList());
    }

    // 특정 카테고리에 속하는 게시물 조회
    public List<PostTitleContentDto> getCategoryPost(Long categoryId) {

        List<Post> postList = postMapper.getCategoryPost(categoryId);
        return postList.stream().map(PostTitleContentDto::from).collect(Collectors.toList());
    }

    public List<PostCountByUserDto> getPostCountByUser() {

        return postMapper.getPostCountByUser();
    }

    public List<PostTitleContentCreateAtDto> getUserLatestPost(Long postId, Long limit) {

        List<Post> postList = postMapper.getUserLatestPost(postId, limit);
        return postList.stream().map(PostTitleContentCreateAtDto::from)
            .collect(Collectors.toList());
    }

    // 각 게시물별 댓글 수 조회
    public List<PostCommentCountDto> getCommentCountByPost() {

        return postMapper.getCommentCountByPost();
    }

    // 특정 카테고리의 게시물 중 가장 많이 조회된 게시물
    public List<PostTitleViewDto> getCategoryMostViewPost(Long categoryId, Long limit) {

        List<Post> postList = postMapper.getCategoryMostViewPost(categoryId, limit);
        return postList.stream().map(PostTitleViewDto::from).collect(Collectors.toList());
    }

    // 특정 기간 동안 작성된 게시물 조회
    public List<PostTitleCreateAtDto> getPreMonthPost(Long month) {

        List<Post> postList = postMapper.getPreMonthPost(month);
        return postList.stream().map(PostTitleCreateAtDto::from).collect(Collectors.toList());
    }

    // 다수의 게시물 조회
    public List<PostDto> getCustomPost(OrderByForm orderByForm, Long offset, Long page) {

        List<Post> postList = postMapper.getCustomPost(orderByForm, offset, page);
        return postList.stream().map(PostDto::from).collect(Collectors.toList());
    }


    // 특정 게시물의 조회수 증가
    public PostDto updateViewCountPost(Long postId) {

        Long postUpdateCount = postMapper.updateViewCountPost(postId);

        if (postUpdateCount >= 0) {
            return PostDto.from(postMapper.getPostById(postId));
        } else {
            return null;
        }
    }

    // 특정 카테고리의 모든 게시물 상태 변경
    public List<PostTitleContentDto> updatePostStatusOfCategory(
        Long categoryId, PostStatusForm postStatusForm) {

        Long postUpdateCount = postMapper.updatePostStatusOfCategory(categoryId, postStatusForm);

        if (postUpdateCount >= 0) {
            List<Post> postList = postMapper.getCategoryPost(categoryId);
            return postList.stream().map(PostTitleContentDto::from).collect(Collectors.toList());
        } else {
            return null;
        }
    }






    // 게시물 생성 메서드
    public int createPost(Post post) {
        return postMapper.createPost(post);
    }

    // 게시물 수정 메서드
    public int updatePost(Long postId, Post post) {

        return postMapper.updatePost(post);
    }

    // 게시물 삭제 메서드
    @Transactional // all or not 원칙으로 하나라도 구문이 실패하면 모두 롤백시킵니다.
    public int deletePost(Long postId) {
        commentMapper.deleteCommentsByPost(postId);
        postCategoriesMapper.deletePostCategoriesByPost(postId);
        return postMapper.deletePost(postId);
    }
}


