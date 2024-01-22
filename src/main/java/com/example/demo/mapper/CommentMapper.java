package com.example.demo.mapper;

import com.example.demo.domain.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentMapper {

    List<Comment> getCommentByPost(Long postId);






    int deleteCommentsByPost(Long postId);
}