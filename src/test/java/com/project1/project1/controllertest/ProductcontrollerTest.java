package com.project1.project1.controllertest;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.project1.Controller.ProductController;
import com.project1.project1.entity.Category;
import com.project1.project1.entity.Products;
import com.project1.project1.services.CategoryService;
import com.project1.project1.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;



import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest(classes = {ProductcontrollerTest.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.project1.project")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductcontrollerTest {
    @Autowired
    MockMvc mockMvc ;

    @Mock
    ProductService productService;


    @InjectMocks
    ProductController productController;
    List<Products> products;
    Category product;
    @BeforeEach
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void test_GetProductList() throws Exception {
        products = new ArrayList<Products>();
        products.add(new Products(1,"name","description",4500,false,false,null,null));
        when(productService.GetProductList()).thenReturn(products);
        this.mockMvc.perform(get("/getallproduct"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void test_getProductById() throws Exception {

        Integer productId=1;
        Products pod1 =new Products(1,"name","category1is",4500,false,false,null,null);
        when(this.productService.GetProductById(productId)).thenReturn(pod1);
        this.mockMvc.perform(get("/getbyproduct{id}",productId))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test

    public void test_deleteProduct() throws Exception {
        Products pod1 =new Products(1,"name","category1is",4500,false,false,null,null);
        int productId=1;
        when(productService.GetProductById(productId)).thenReturn(pod1);
        this.mockMvc.perform(delete("/deleteproduct{id}",productId))
                .andExpect(status().isOk());
    }


}

