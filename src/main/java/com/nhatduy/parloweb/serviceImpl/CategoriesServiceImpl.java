package com.nhatduy.parloweb.serviceImpl;

import com.nhatduy.parloweb.dao.CategoriesRepository;
import com.nhatduy.parloweb.dto.CategoriesDTO;
import com.nhatduy.parloweb.entity.Categories;
import com.nhatduy.parloweb.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public List<CategoriesDTO> findAll() {
        return categoriesRepository.findAll().stream()
                .map(categories -> new CategoriesDTO(categories.getID(),categories.getParentID(),categories.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(CategoriesDTO categoriesDTO) {
        categoriesRepository.save(convertToModel(categoriesDTO));
    }

    public Categories convertToModel(CategoriesDTO categoriesDTO){
        Categories categories = new Categories();
        categories.setID(categoriesDTO.getID());
        categories.setParentID(categoriesDTO.getParentID());
        categories.setTitle(categoriesDTO.getTitle());
        return categories;
    }

}
