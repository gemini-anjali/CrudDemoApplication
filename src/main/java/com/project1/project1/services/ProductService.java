package com.project1.project1.services;



import com.project1.project1.entity.Products;

import java.util.List;

public interface ProductService {
    List<Products> GetProductList();

    Products GetProductById(Integer id);

    public Products DeleteProduct(Integer id);




}