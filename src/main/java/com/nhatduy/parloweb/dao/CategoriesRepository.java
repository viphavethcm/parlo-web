package com.nhatduy.parloweb.dao;

import com.nhatduy.parloweb.entity.Categories;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
    @EntityGraph("Categories.sub_categories")
    List<Categories> findAll();
}
