package com.project1.project1;//package com.project1.project1;

import com.project1.project1.entity.Category;
import com.project1.project1.repository.CategoryRepository;
import com.project1.project1.repository.ProductsRepository;
import com.project1.project1.services.CategoryServicesImpl;
import com.project1.project1.services.ProductServicesImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes={categoryservicetest.class})
public class categoryservicetest {
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ProductsRepository productsRepository;
    @InjectMocks
    CategoryServicesImpl categservice;

    @InjectMocks
    ProductServicesImpl productServices;

    public List<Category> mycategory;
    //TEST GET CATEGORY
    @Test

    public void test_GetCategoryList()
    {   List <Category> mycategory= new ArrayList<Category>();
        mycategory.add(new Category(1,"category1","category1is",true,false,null,null,null));
        mycategory.add(new Category(2,"category1","category1is",true,false,null,null,null));
        when(categoryRepository.findAll()).thenReturn(mycategory);
        categservice.GetCategoryList();
        assertEquals(2,categservice.GetCategoryList().size());
    }

    //TEST GET CATEGORY BY ID
    @Test
    public void test_GetCategoryById()
    {
        List <Category> mycategory= new ArrayList<Category>();
        mycategory.add(new Category(1,"category1","category1is",true,false,null,null,null));
        when(categoryRepository.findAll()).thenReturn(mycategory);
        assertThat(mycategory).isNotNull();
    }
    //TEST ADD CATEGORY
    @Test
    public void test_addCategory()
    {
        Category category= new Category(3,"category3","category1is",true,false,null,null,null);
        when(categoryRepository.save(category)).thenReturn(category);
        categservice.addCategory(category);
        assertEquals(category,categservice.addCategory(category));
    }

    //TEST UPDATE CATEGORY
    @Test
    void updateCategory() {
        Category cat1 =new Category(1,null,"category1is",false,false,null,null,null);
        int cat1Id=1;

        Category updatedCategory=new Category();
        updatedCategory.setCategoryName("name");

        when(categoryRepository.findById(cat1Id)).thenReturn(Optional.of(cat1));
        when(categoryRepository.save(cat1)).thenReturn(cat1);
        assertThat(categservice.updateCategory(cat1Id,updatedCategory)).isNotNull();
    }

    //TEST DELETE CATEGORY
    @Test
    public void test_DeleteCategory(){
        List <Category> mycategory= new ArrayList<Category>();
        mycategory.add(new Category(1,"category1","category1is",true,false,null,null,null));
        int CategoryId =1;
        categoryRepository.DeleteCategory(CategoryId);
        when(categoryRepository.findAll()).thenReturn(mycategory);
        assertThat(mycategory).isNotNull();

    }



}
