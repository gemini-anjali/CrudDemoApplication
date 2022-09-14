package com.project1.project1.services;


import com.project1.project1.entity.Products;
import com.project1.project1.exception.BusinessException;
import com.project1.project1.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ProductServicesImpl implements ProductService {
    @Autowired
    ProductsRepository productsRepository;
    @Override
    public List<Products> GetProductList() {
        try {
            List<Products> productList  = productsRepository.findAll();
            if(productList.isEmpty())
                throw new BusinessException("604","list is completely empty");
            return productList;
        }
        catch (Exception e)
        {
            throw new BusinessException("605","something went wrong"+e.getMessage());
        }
    }

    @Override
    public Products GetProductById(Integer productid) {
        try {
            if(productid==null)
            {
                throw new BusinessException("601","plz send proper id");
            }
            return productsRepository.findById(productid).get();
        } catch (java.util.NoSuchElementException e){
            throw new BusinessException("606", "given employee is null"+e.getMessage());
        }

    }

    @Override
    public Products DeleteProduct(Integer productid) {
        try {
            if (productid == null) {
                throw new BusinessException("601", "plz send proper id");
            }

            productsRepository.DeleteProduct(productid);
            return null;
        }
        catch (java.util.NoSuchElementException e){
                throw new BusinessException("606", "given employee is null"+e.getMessage());
            }

        }
}
