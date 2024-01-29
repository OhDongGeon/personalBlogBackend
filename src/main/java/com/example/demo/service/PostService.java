package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostCategoriesMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.model.common.dto.PostCategoryNameDto;
import com.example.demo.model.common.form.ManyStandardId;
import com.example.demo.model.common.form.OrderByForm;
import com.example.demo.model.common.form.StandardDateForm;
import com.example.demo.model.post.dto.PostBestListDto;
import com.example.demo.model.post.dto.PostCommentCountDto;
import com.example.demo.model.post.dto.PostCountByUserDto;
import com.example.demo.model.post.dto.PostDto;
import com.example.demo.model.post.dto.PostTitleContentCreateAtDto;
import com.example.demo.model.post.dto.PostTitleContentDto;
import com.example.demo.model.post.dto.PostTitleContentViewDto;
import com.example.demo.model.post.dto.PostTitleCreateAtDto;
import com.example.demo.model.post.dto.PostTitleViewDto;
import com.example.demo.model.post.form.PostStatusForm;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public List<PostTitleContentViewDto> getStatusPost(PostStatusForm postStatusForm) {

        List<Post> postList = postMapper.getStatusPost(postStatusForm);
        return postList.stream().map(PostTitleContentViewDto::from).collect(Collectors.toList());
    }

    // 특정 사용자의 게시물 조회
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

    // 각 사용자별 게시물 수 조회
    public List<PostCountByUserDto> getPostCountByUser() {

        return postMapper.getPostCountByUser();
    }

    // 특정 사용자의 최근 게시물 조회
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

    // Best 포스트 조회
    @Transactional
    public PostBestListDto getBestPost(Long limit) {

        // 7일간 많은 댓글이 달린 게시물 조회 (기준 * 2)
        List<Post> mostCommentPosts = postMapper.getMostCommentByPost(limit * 2);
        // 가장 많이 조회된 게시물 조회 (기준 * 2)
        List<Post> mostViewPosts = postMapper.getMostViewPost(limit * 2);

        int intLimit = limit.intValue();

        // 7일간 많은 댓글이 달린 게시물 조회(기준)
        List<Post> mostCommentPostsLimit = mostCommentPosts.subList(0, intLimit);
        // 7일간 많은 댓글이 달린 게시물 조회(기준)
        List<Post> mostViewPostsLimit = mostViewPosts.subList(0, intLimit);

        // 중복없는 베스트 게시물
        Set<Post> bestPosts = new HashSet<>(mostCommentPostsLimit);
        for (Post mostViewPost : mostViewPosts) {
            bestPosts.add(mostViewPost);

            if (bestPosts.size() >= limit * 2) {
                break;
            }
        }

        return PostBestListDto.of(mostCommentPostsLimit, mostViewPostsLimit, bestPosts);
    }

    // 다수의 게시물 조회
    public List<PostCategoryNameDto> getCustomPost(OrderByForm orderByForm, Long offset,
        Long page) {

        return postMapper.getCustomPost(orderByForm, offset, page);
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


    // 게시물 삭제
    @Transactional
    public Long deletePost(Long postId) {

        commentMapper.deleteCommentsByPost(postId);

        postCategoriesMapper.deletePostCategoriesByPost(postId);

        return postMapper.deletePost(postId);
    }

    // 특정 카테고리에 속한 게시물 삭제
    @Transactional
    public Long deleteCategoryPost(Long categoryId) {

        List<Post> postList = postMapper.getCategoryPost(categoryId);
        Long deleteCount = 0L;

        if (!postList.isEmpty()) {
            List<Long> postIdList = postList.stream().map(Post::getPostId).toList();

            commentMapper.deleteCommentByPostList(postIdList);

            postCategoriesMapper.deletePostCategoriesByPostList(postIdList);

            deleteCount = postMapper.deletePostList(postIdList);
        }
        return deleteCount;
    }

    // 특정 상태의 게시물 삭제
    @Transactional
    public Long deletePostStatus(PostStatusForm postStatusForm) {

        List<Post> postList = postMapper.getStatusPost(postStatusForm);
        Long deleteCount = 0L;

        if (!postList.isEmpty()) {
            List<Long> postIdList = postList.stream().map(Post::getPostId).toList();

            commentMapper.deleteCommentByPostList(postIdList);

            postCategoriesMapper.deletePostCategoriesByPostList(postIdList);

            deleteCount = postMapper.deletePostList(postIdList);
        }
        return deleteCount;
    }

    // 조회수가 낮은 게시물 삭제
    public Long deletePostLowView(Long limit) {

        List<Post> postList = postMapper.getPostLowView(limit);
        Long deleteCount = 0L;

        if (!postList.isEmpty()) {
            List<Long> postIdList = postList.stream().map(Post::getPostId).toList();

            commentMapper.deleteCommentByPostList(postIdList);

            postCategoriesMapper.deletePostCategoriesByPostList(postIdList);

            deleteCount = postMapper.deletePostList(postIdList);
        }
        return deleteCount;
    }

    // 특정 날짜 이전에 작성된 게시물과 그 댓글 삭제
    public Long deleteBeforeDate(StandardDateForm standardDateForm) {

        List<Post> postList = postMapper.getBeforeDate(standardDateForm);
        Long deleteCount = 0L;

        if (!postList.isEmpty()) {
            List<Long> postIdList = postList.stream().map(Post::getPostId).toList();

            commentMapper.deleteCommentByPostList(postIdList);

            postCategoriesMapper.deletePostCategoriesByPostList(postIdList);

            deleteCount = postMapper.deletePostList(postIdList);
        }
        return deleteCount;
    }

    // 다수의 게시물 삭제
    public Long deleteManyId(ManyStandardId manyStandardId) {

        commentMapper.deleteCommentByPostList(manyStandardId.getStandardId());

        postCategoriesMapper.deletePostCategoriesByPostList(manyStandardId.getStandardId());

        return postMapper.deletePostList(manyStandardId.getStandardId());
    }


    // 게시물 생성 메서드
    public Post createPost(Post post) {

        postMapper.createPost(post);

        return post;
    }

    // 게시물 수정 메서드
    public int updatePost(Long postId, Post post) {

        return postMapper.updatePost(post);
    }
}


