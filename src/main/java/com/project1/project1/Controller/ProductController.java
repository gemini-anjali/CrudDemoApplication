package com.project1.project1.Controller;


import com.project1.project1.entity.Category;
import com.project1.project1.entity.Products;
import com.project1.project1.exception.BusinessException;
import com.project1.project1.exception.ControllerException;
import com.project1.project1.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
//@RequestMapping("/apii")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("getallproduct")
    @Operation(summary = "get all product details")
    public ResponseEntity<List<Products>> GetProductList() {

        try {
            log.info("get category");
            List<Products> productList = productService.GetProductList();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
        catch(Exception e)
        {   log.error("Product is empty");
            throw new BusinessException("605","List is empty");
        }

    }
    @GetMapping("getbyproduct{id}")
    @Operation(summary = "get by product Id")
    public ResponseEntity<?> GetProductById(@PathVariable Integer id) {
        try {
            log.info("Get Category By Id");
            Products product = this.productService.GetProductById(id);
            return new ResponseEntity<Products>(product, HttpStatus.OK);
        }
        catch (BusinessException e) {
            log.error("No category Id found");
            ControllerException ce = new ControllerException(e.getErrorcode(),e.getErrormessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.NOT_FOUND);

        }
    }
    @PutMapping("updateproduct{Id}")
    @Operation(summary = "update product")
    public ResponseEntity<?> updateProduct(@PathVariable("Id") Integer ProductId, @RequestBody Products product) {
        try{
            log.info("Update product");
            productService.updateProduct(ProductId,product );
            return new ResponseEntity<>(productService.GetProductById(ProductId), HttpStatus.OK);
        }
        catch (BusinessException e) {
            log.error("No product Id found");
            ControllerException ce = new ControllerException(e.getErrorcode(),e.getErrormessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("deleteproduct{id}")
    @Operation(summary = "delete product")
    public ResponseEntity<?> DeleteProduct(@PathVariable("id") Integer id) {
        try {
            log.info("category deleted");
            productService.DeleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (BusinessException e) {
            log.error("No category Id found");
            ControllerException ce = new ControllerException(e.getErrorcode(),e.getErrormessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.NOT_FOUND);

        }
    }
}
