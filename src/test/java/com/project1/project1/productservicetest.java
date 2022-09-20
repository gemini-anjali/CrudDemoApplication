package com.project1.project1;
import com.project1.project1.entity.Category;
import com.project1.project1.entity.Products;
import com.project1.project1.repository.ProductsRepository;
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

@SpringBootTest(classes={productservicetest.class})
public class productservicetest {

    @Mock
    ProductsRepository productsRepository;

    @InjectMocks
    ProductServicesImpl productServices;

    public List<Category> mycategory;


    //TEST GET PRODUCT
    @Test
    public void test_GetProductList()
    {   List <Products> myproduct= new ArrayList<Products>();
        myproduct.add(new Products(1,"category1","category1is",100,false,false,null,null));
        myproduct.add(new Products(2,"category1","category1is",100,false,false,null,null));
        when(productsRepository.findAll()).thenReturn(myproduct);
        productServices.GetProductList();
        assertEquals(2,productServices.GetProductList().size());
    }

    //TEST GET PRODUCT BY ID
    @Test
    public void test_GetProductById()
    {
        List <Products> mylist= new ArrayList<Products>();
        mylist.add(new Products(1,"category1","category1is",80,false,false,null,null));
        when(productsRepository.findAll()).thenReturn(mylist);
        assertThat(mylist).isNotNull();
    }

    //TEST UPDATE PRODUCT
    @Test
    void updateProduct() {
        Products prod1 =new Products(1,null,"category1is",4500,false,false,null,null);
        int cat1Id=1;

        Products updatedProduct=new Products();
        updatedProduct.setProductName("name");

        when(productsRepository.findById(cat1Id)).thenReturn(Optional.of(prod1));
        when(productsRepository.save(prod1)).thenReturn(prod1);
        assertThat(productServices.updateProduct(cat1Id,updatedProduct)).isNotNull();
    }

    //TEST DELETE PRODUCT
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

