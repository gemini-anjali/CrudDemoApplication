package com.project1.project1.Controller;

import com.project1.project1.entity.Category;
import com.project1.project1.exception.BusinessException;
import com.project1.project1.exception.ControllerException;
import com.project1.project1.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController

public class CategoryController {
    @Autowired
    private CategoryService categoryservices;

    @GetMapping("getallcategory")
    @Operation(summary = "getting all the category")
    public ResponseEntity<List<Category>> GetCategoryList() {
        log.info("get all the data");
        try {
            List<Category> categoryList = categoryservices.GetCategoryList();
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        }
        catch(Exception e)
        {   log.error("data not present");
            throw new BusinessException("605","List is empty");
        }

    }
    @PostMapping("create")
    @Operation(summary = "creating the data")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        try {
            log.info("category added");
            Category categ =  categoryservices.addCategory(category);
            return new ResponseEntity<>(categ, HttpStatus.CREATED);
        } catch (BusinessException e) {
            log.error("category cannot be added");
            ControllerException ce = new ControllerException(e.getErrorcode(), e.getErrormessage());
            return new ResponseEntity<Category>((MultiValueMap<String, String>) ce,HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping("getbycategory{id}")
    @Operation(summary = "getting the data by id")
    public ResponseEntity<?> GetCategoryById(@PathVariable Integer id)  {
        try {
            log.info("Get Category By Id");
            Category category = this.categoryservices.GetCategoryById(id);
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("No category Id found");
            ControllerException ce = new ControllerException(e.getErrorcode(),e.getErrormessage());
           return new ResponseEntity<ControllerException>(ce,HttpStatus.NOT_FOUND);

        }
    }
    @PutMapping("updatecategory{CategoryId}")
    @Operation(summary = "update category")
    public ResponseEntity<?> updateCategory(@PathVariable("CategoryId") Integer CategoryId, @RequestBody Category category) {
        try{
            log.info("Update Category");
            categoryservices.updateCategory(CategoryId,category );
            return new ResponseEntity<>(categoryservices.GetCategoryById(CategoryId), HttpStatus.OK);
        }
        catch (BusinessException e) {
            log.error("No category Id found");
            ControllerException ce = new ControllerException(e.getErrorcode(),e.getErrormessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("deletecategory{id}")
    public ResponseEntity<?> DeleteCategory(@PathVariable("id") Integer id) {
        try {
            log.info("category deleted");
            categoryservices.DeleteCategory(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (BusinessException e) {
            log.error("No category Id found");
            ControllerException ce = new ControllerException(e.getErrorcode(),e.getErrormessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.NOT_FOUND);

        }
    }

}