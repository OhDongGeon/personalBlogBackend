package com.example.demo.service;

import com.example.demo.domain.Comment;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.model.comment.dto.CommentDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;


    // 특정 게시물에 대한 모든 댓글 조회
    public List<CommentDto> getCommentByPost(Long postId) {

        List<Comment> commentList = commentMapper.getCommentByPost(postId);
        return commentList.stream().map(CommentDto::from).collect(Collectors.toList());
    }


    // 특정 사용자가 작성한 댓글 삭제
    @Transactional
    public Long deleteComment(Long commentId, Long userId) {

        List<Comment> replyList = commentMapper.getReply(commentId);
        Long deleteCount = 0L;

        if (replyList.stream().allMatch(col -> col.getUserId().equals(userId))) {
            List<Long> commentList = replyList.stream().map(Comment::getCommentId).toList();

            deleteCount = commentMapper.deleteCommentList(commentList);
        }
        // exception 추가 예정
        return deleteCount;
    }

    // 오래된 댓글 삭제
    @Transactional
    public Long deletePreMonthComment(Long month) {

        return commentMapper.deletePreMonthComment(month);
    }
}
