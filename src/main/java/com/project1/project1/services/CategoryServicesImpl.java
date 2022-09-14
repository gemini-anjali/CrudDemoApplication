package com.project1.project1.services;


import com.project1.project1.entity.Category;
import com.project1.project1.exception.BusinessException;
import com.project1.project1.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class CategoryServicesImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryrepository;
    @Override
    public List<Category> GetCategoryList() {
        try {
            List<Category> categoryList  = categoryrepository.findAll();
            if(categoryList.isEmpty())
                throw new BusinessException("604","list is completely empty");
            return categoryList;
        }
        catch (Exception e)
        {
            throw new BusinessException("605","something went wrong"+e.getMessage());
        }
    }


    @Override
    public Category updateCategory(Integer id, Category category) {
        try { if(category==null)
        {
            throw new BusinessException("601","plz send proper id");
        }
            Category updateCategory = categoryrepository.findById(id).get();
            updateCategory.setCategoryName(category.getCategoryName());
            updateCategory.setCategoryDescription(category.getCategoryDescription());
            updateCategory.setProducts(category.getProducts());
            return updateCategory;
        }
        catch (Exception e){
            throw new BusinessException("603", "something went wrong"+e.getMessage());
        }
    }
      @Override
    public Category addCategory(Category category)
    {
        try {
            if(category==null)
            {
                throw new BusinessException("601","plz send proper id");
            }
            return categoryrepository.save(category);
        } catch (Exception e){
            throw new BusinessException("603", "something went wrong"+e.getMessage());
        }
    }
    @Override
    public Category GetCategoryById(Integer categoryid) {
        try {
            if(categoryid==null)
            {
                throw new BusinessException("601","plz send proper id");
            }
            return categoryrepository.findById(categoryid).get();
        } catch (java.util.NoSuchElementException e){
            throw new BusinessException("606", "given employee is null"+e.getMessage());
        }

    }
    @Override
    public Category DeleteCategory(Integer categoryid) {
        try {
            categoryrepository.DeleteCategory(categoryid);
            return null;
        } catch (IllegalArgumentException e) {
            throw new BusinessException("608", "given employee id is null");
        }
    }
}
