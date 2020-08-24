package com.nhatduy.parloweb.serviceImpl;

import com.nhatduy.parloweb.dao.CategoriesRepository;
import com.nhatduy.parloweb.dto.CategoriesDTO;
import com.nhatduy.parloweb.dto.Sub_CategoriesDTO;
import com.nhatduy.parloweb.entity.Categories;
import com.nhatduy.parloweb.entity.Sub_Categories;
import com.nhatduy.parloweb.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;


    @Override
    public List<CategoriesDTO> findAll() {
        List<CategoriesDTO> list = new ArrayList<>();
        for (Categories categories:categoriesRepository.findAll()){
            list.add(converToDTO(categories));
        }
        return list;
    }

    public CategoriesDTO converToDTO(Categories categories){
        List<Sub_CategoriesDTO> listSub = new ArrayList<>();
        CategoriesDTO categoriesDTO = new CategoriesDTO();
        categoriesDTO.setCategoryID(categories.getCategoryID());
        categoriesDTO.setCategoryName(categories.getCategoryName());
        categoriesDTO.setCreated_Date(categories.getCreated_Date());
        categoriesDTO.setModified_Date(categories.getModified_Date());
        for (Sub_Categories sub_categories:categories.getSub_categories()){
            Sub_CategoriesDTO sub_categoriesDTO = new Sub_CategoriesDTO();
            sub_categoriesDTO.setSub_CategoryID(sub_categories.getSub_CategoryID());
            sub_categoriesDTO.setSub_categoryName(sub_categories.getSub_categoryName());
            sub_categoriesDTO.setCreated_Date(sub_categories.getCreated_Date());
            sub_categoriesDTO.setModified_Date(sub_categories.getModified_Date());
            listSub.add(sub_categoriesDTO);
        }
        categoriesDTO.setSub_categoriesDTO(listSub);
        return categoriesDTO;
    }

}
