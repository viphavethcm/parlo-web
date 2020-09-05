package com.nhatduy.parloweb.dao;

import com.nhatduy.parloweb.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CategoriesRepository extends JpaRepository<Categories,Integer> {

    @Query("select c from Categories c order by c.title")
    List<Categories> findAll();
}
