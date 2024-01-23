package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CategoriesMapper {

    Long deleteCategory(Long categoryId);
}
