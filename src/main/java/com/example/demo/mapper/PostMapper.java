package com.example.demo.mapper;

import com.example.demo.domain.Post;
import com.example.demo.model.common.form.OrderByForm;
import com.example.demo.model.post.dto.PostCommentCountDto;
import com.example.demo.model.post.dto.PostCountByUserDto;
import com.example.demo.model.post.form.PostStatusForm;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PostMapper {

    // 게시물 조회
    Post getPostById(Long postId);

    List<Post> getAllPosts();

    // 공개된 모든 게시물 조회
    List<Post> getStatusPublicPost();

    List<Post> getUserPost(Long userId);

    // 가장 많이 조회된 상위 5개 게시물 조회
    List<Post> getMostViewPost(Long limit);

    // 특정 카테고리에 속하는 게시물 조회
    List<Post> getCategoryPost(Long categoryId);

    List<PostCountByUserDto> getPostCountByUser();

    List<Post> getUserLatestPost(Long postId, Long limit);

    // 각 게시물별 댓글 수 조회
    List<PostCommentCountDto> getCommentCountByPost();

    // 특정 카테고리의 게시물 중 가장 많이 조회된 게시물
    List<Post> getCategoryMostViewPost(Long categoryId, Long limit);

    // 특정 기간 동안 작성된 게시물 조회
    List<Post> getPreMonthPost(Long month);

    // 다수의 게시물 조회
    List<Post> getCustomPost(OrderByForm orderByForm, Long offset, Long page);

    // 특정 게시물의 조회수 증가
    Long updateViewCountPost(Long postId);

    // 특정 카테고리의 모든 게시물 상태 변경
    Long updatePostStatusOfCategory(Long categoryId, PostStatusForm postStatusForm);





    int createPost(Post post);

    int updatePost(Post post);

    int deletePost(Long postId);
}