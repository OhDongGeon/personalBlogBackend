package com.example.demo.service;

import com.example.demo.domain.Comment;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.model.comment.dto.CommentDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public List<CommentDto> getCommentByPost(Long postId) {

        List<Comment> commentList = commentMapper.getCommentByPost(postId);
        return commentList.stream().map(CommentDto::from).collect(Collectors.toList());
    }
}
