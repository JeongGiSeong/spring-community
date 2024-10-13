package com.JKS.community.board.service;

import com.JKS.community.board.dto.CategoryDto;
import com.JKS.community.board.dto.CategoryFormDto;
import com.JKS.community.board.domain.Category;
import com.JKS.community.exception.CategoryNotFoundException;
import com.JKS.community.board.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto create(CategoryFormDto categoryFormDto) {
        if (categoryFormDto.getParentId() != null) {
            Category parent = categoryRepository.findById(categoryFormDto.getParentId()).orElse(null);
            Category category = Category.of(categoryFormDto.getName(), parent, true);
            categoryRepository.save(category);
            return new CategoryDto(category);
        } else {
            Category category = Category.of(categoryFormDto.getName(), null, true);
            categoryRepository.save(category);
            return new CategoryDto(category);
        }
    }

    @Override
    public List<CategoryDto> getListByDepth(int depth) {
        List<Category> categoryList = categoryRepository.findAllByDepth(depth);
        return categoryList.stream().map(CategoryDto::new).toList();
    }

    @Override
    public CategoryDto get(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Invalid category Id:" + id));
        return new CategoryDto(category);
    }

    @Override
    public void update(Long id, CategoryFormDto categoryFormDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Invalid category Id:" + id));
        Category parent = categoryRepository.findById(categoryFormDto.getParentId()).orElseThrow(() -> new CategoryNotFoundException("Invalid category Id:" + id));
        category.update(categoryFormDto, parent);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Invalid category Id:" + id));
        categoryRepository.delete(category);
    }
}
