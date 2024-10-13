package com.JKS.community.board.repository;

import com.JKS.community.board.domain.Category;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> findAllByDepth(int depth);
}