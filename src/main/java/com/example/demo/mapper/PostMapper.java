package com.example.demo.mapper;

import com.example.demo.domain.Post;
import com.example.demo.model.common.form.OrderByForm;
import com.example.demo.model.post.dto.PostCommentCountDto;
import com.example.demo.model.post.dto.PostCountByUserDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PostMapper {

    List<Post> getStatusPublicPost();

    List<Post> getUserPost(Long userId);

    List<Post> getMostViewPost(Long limit);

    List<Post> getCategoryPost(Long categoryId);

    List<PostCountByUserDto> getPostCountByUser();

    List<Post> getUserLatestPost(Long postId, Long limit);

    List<PostCommentCountDto> getCommentCountByPost();

    List<Post> getCategoryMostViewPost(Long categoryId, Long limit);

    List<Post> getPreMonthPost(Long month);

    List<Post> getCustomPost(OrderByForm orderByForm, Long offset, Long page);






    // 게시물 조회
//    @Select("SELECT * FROM Posts WHERE post_id = #{postId}")
    Post getPostById(Long postId);

    List<Post> getAllPosts();

    int createPost(Post post);

    int updatePost(Post post);

    int deletePost(Long postId);

    // 게시물 목록 조회, 생성, 수정, 삭제 등의 메소드 추가
}