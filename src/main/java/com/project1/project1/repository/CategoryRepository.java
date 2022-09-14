package com.project1.project1.repository;

import com.project1.project1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    @Modifying
    @Transactional
    @Query(value = "update category set Deleted = true,Active = false where category_id = :categoryid",nativeQuery = true)
    public void DeleteCategory(@Param("categoryid") Integer categoryid);


}