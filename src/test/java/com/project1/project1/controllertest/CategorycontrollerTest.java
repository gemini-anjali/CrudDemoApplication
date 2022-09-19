package com.project1.project1.controllertest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.project1.Controller.CategoryController;
import com.project1.project1.entity.Category;
import com.project1.project1.services.CategoryService;
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
@SpringBootTest(classes = {CategorycontrollerTest.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.project1.project")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategorycontrollerTest {
    @Autowired
    MockMvc mockMvc ;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;
    List<Category> categories;
    Category category;
    @BeforeEach
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void test_GetCategoryList() throws Exception {
       categories = new ArrayList<Category>();
       categories.add(new Category(1,"name","description",false,false,null,null,null));
       when(categoryService.GetCategoryList()).thenReturn(categories);
       this.mockMvc.perform(get("/getallcategory"))
               .andExpect(status().isOk())
               .andDo(print());
    }


    @Test
    void test_getCategoryById() throws Exception {

        Integer categoryId=1;
        Category cat1 =new Category(1,null,"category1is",false,false,null,null,null);
        when(this.categoryService.GetCategoryById(categoryId)).thenReturn(cat1);
        this.mockMvc.perform(get("/getbycategory{id}",categoryId))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    public void test_addcategory() throws Exception {
        category = new Category(1, "name", "description", false, false, null, null, null);

        when(categoryService.addCategory(category)).thenReturn(category);
        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(category);
        this.mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonbody))

                .andExpect(status().isCreated())
                .andDo(print());
    }

     @Test
    public void test_UpdateCategory() throws Exception {
         category = new Category(1, "name", "description", false, false, null, null, null);
        int categoryId = 1;
        when(categoryService.GetCategoryById(categoryId)).thenReturn(category);
        when(categoryService.updateCategory(categoryId,category)).thenReturn(category);
         when(categoryService.addCategory(category)).thenReturn(category);
         ObjectMapper mapper = new ObjectMapper();
         String jsonbody = mapper.writeValueAsString(category);
         this.mockMvc.perform(put("/updatecategory{CategoryId}",categoryId)
                         .contentType(MediaType.APPLICATION_JSON)
                         .content(jsonbody))

                 .andExpect(status().isOk())
                 .andDo(print());

     }

     @Test

    public void test_deleteCategory() throws Exception {
         category = new Category(1, "name", "description", false, false, null, null, null);
        int categoryId=1;
        when(categoryService.GetCategoryById(categoryId)).thenReturn(category);
        this.mockMvc.perform(delete("/deletecategory{id}",categoryId))
                .andExpect(status().isOk());
     }


}

