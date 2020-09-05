package com.nhatduy.parloweb.service;

import com.nhatduy.parloweb.dto.CategoriesDTO;

import java.util.List;

public interface CategoriesService {

    List<CategoriesDTO> findAll();//get List Categories

    void save(CategoriesDTO categoriesDTO);// Save or Update new Categories

}
