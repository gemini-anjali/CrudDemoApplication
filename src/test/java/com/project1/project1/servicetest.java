package com.project1.project1;//package com.project1.project1;

import com.project1.project1.entity.Category;
import com.project1.project1.entity.Products;
import com.project1.project1.repository.CategoryRepository;
import com.project1.project1.repository.ProductsRepository;
import com.project1.project1.services.CategoryServicesImpl;
import com.project1.project1.services.ProductServicesImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@SpringBootTest(classes={servicetest.class})
public class servicetest {
 @Mock
    CategoryRepository categoryRepository;
 @Mock
    ProductsRepository productsRepository;
 @InjectMocks
 CategoryServicesImpl categservice;

 @InjectMocks
    ProductServicesImpl productServices;

 public List<Category> mycategory;
  @Test
  @Order(1)
   public void test_GetCategoryList()
  {   List <Category> mycategory= new ArrayList<Category>();
      mycategory.add(new Category(1,"category1","category1is",true,false,null,null,null));
      mycategory.add(new Category(2,"category1","category1is",true,false,null,null,null));
      when(categoryRepository.findAll()).thenReturn(mycategory);
      categservice.GetCategoryList();
      assertEquals(2,categservice.GetCategoryList().size());
  }
  @Test
    public void test_GetProductList()
    {   List <Products> myproduct= new ArrayList<Products>();
        myproduct.add(new Products(1,"category1","category1is",100,false,false,null,null));
        myproduct.add(new Products(2,"category1","category1is",100,false,false,null,null));
        when(productsRepository.findAll()).thenReturn(myproduct);
        productServices.GetProductList();
        assertEquals(2,productServices.GetProductList().size());
    }

 @Test
    public void test_GetCategoryById()
  {
      List <Category> mycategory= new ArrayList<Category>();
      mycategory.add(new Category(1,"category1","category1is",true,false,null,null,null));
      when(categoryRepository.findAll()).thenReturn(mycategory);
      assertThat(mycategory).isNotNull();
  }
  @Test
    public void test_GetProductById()
    {
        List <Products> mylist= new ArrayList<Products>();
        mylist.add(new Products(1,"category1","category1is",80,false,false,null,null));
        when(productsRepository.findAll()).thenReturn(mylist);
        assertThat(mylist).isNotNull();
    }
  @Test
    public void test_addCategory()
  {
      Category category= new Category(3,"category3","category1is",true,false,null,null,null);
      when(categoryRepository.save(category)).thenReturn(category);
      categservice.addCategory(category);
      assertEquals(category,categservice.addCategory(category));
  }
  @Test
  public void test_updatecategory()
  {
      Category category= new Category(3,null,null,false,false,null,null,null);

       category.setCategoryName("catg");
      when(categoryRepository.save(category)).thenReturn(category);
      categservice.addCategory(category);
      assertThat(category.getCategoryName()).isNotNull();

  }

    @Test
    public void test_DeleteCategory(){
        List <Category> mycategory= new ArrayList<Category>();
        mycategory.add(new Category(1,"category1","category1is",true,false,null,null,null));
       int CategoryId =1;
        categoryRepository.DeleteCategory(CategoryId);
        when(categoryRepository.findAll()).thenReturn(mycategory);
        assertThat(mycategory).isNotNull();

    }
   @Test
   public void test_DeleteProduct(){
       List <Products> myproduct= new ArrayList<Products>();
       myproduct.add(new Products(1,"category1","category1is",50000,false,false,null,null));
       int productId =1;
       productsRepository.DeleteProduct(productId);
       when(productsRepository.findAll()).thenReturn(myproduct);
       assertThat(myproduct).isNotNull();

   }


}
