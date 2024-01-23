package com.example.demo.mapper;

import com.example.demo.domain.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentMapper {

    // 특정 게시물에 대한 모든 댓글 조회
    List<Comment> getCommentByPost(Long postId);

    // 댓글 기준 하위 댓글 조회
    List<Comment> getReply(Long commentId);


    // 게시물의 댓글 삭제
    void deleteCommentsByPost(Long postId);

    // 사용자 기준 댓글 삭제
    void deleteCommentByUser(Long userId);

    // 게시물에 해당된 모든 댓글 삭제
    void deleteCommentByPostList(List<Long> postId);

    // 오래된 댓글 삭제
    Long deletePreMonthComment(Long month);

    // 댓글 및 하위 댓글 삭제
    Long deleteCommentList(List<Long> commentList);
}

