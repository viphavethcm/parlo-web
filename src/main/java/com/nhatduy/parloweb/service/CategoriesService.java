package com.nhatduy.parloweb.service;

import com.nhatduy.parloweb.dto.CategoriesDTO;
import com.nhatduy.parloweb.dto.Sub_CategoriesDTO;

import java.util.List;

public interface CategoriesService {

    List<CategoriesDTO> findAll();//get List Categories

    void save(CategoriesDTO categoriesDTO);// Save or Update new Categories

    void saveSub(CategoriesDTO categoriesDTO);//Save or Update new Sub Categories

    int CHECK_PATTERN(CategoriesDTO categoriesDTO);


}
