package com.project1.project1.repository;


import com.project1.project1.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductsRepository extends JpaRepository <Products,Integer> {

    //FOR PERFORMING SOFT DELETE
    @Modifying
    @Transactional
    @Query(value = "update products set Deleted = true,Active = false where product_id = :productid",nativeQuery = true)
    public void DeleteProduct(@Param("productid") Integer productid);
}
