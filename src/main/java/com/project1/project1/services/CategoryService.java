package com.project1.project1.services;

import com.project1.project1.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> GetCategoryList();

    public Category updateCategory(Integer id, Category category);

    Category GetCategoryById(Integer id);

    public Category DeleteCategory(Integer id);

    Category addCategory(Category category);


}