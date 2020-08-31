package com.nhatduy.parloweb.serviceImpl;

import com.nhatduy.parloweb.dao.CategoriesRepository;
import com.nhatduy.parloweb.dao.SubCategorieRepository;
import com.nhatduy.parloweb.dto.CategoriesDTO;
import com.nhatduy.parloweb.dto.Sub_CategoriesDTO;
import com.nhatduy.parloweb.entity.Categories;
import com.nhatduy.parloweb.entity.Sub_Categories;
import com.nhatduy.parloweb.service.CategoriesService;
import com.nhatduy.parloweb.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    SubCategorieRepository subCategorieRepository;


    @Override
    public List<CategoriesDTO> findAll() {
        List<CategoriesDTO> list = new ArrayList<>();
        for (Categories categories:categoriesRepository.findAll()){
            list.add(converToDTO(categories));
        }
        return list;
    }

    @Override
    public void save(CategoriesDTO categoriesDTO) {
        categoriesRepository.save(convertToModel(categoriesDTO));
    }

    @Override
    public void saveSub(CategoriesDTO categoriesDTO) {
            for (Sub_CategoriesDTO sub_categoriesDTO:categoriesDTO.getSub_categoriesDTO()){
            Sub_Categories sub_categories = new Sub_Categories();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (sub_categoriesDTO.getSub_CategoryID() != 0){
                sub_categoriesDTO.setModified_Date(SystemUtils.convertToDate(timestamp));
            }else {
                sub_categoriesDTO.setCreated_Date(SystemUtils.convertToDate(timestamp));
            }
            sub_categories.setModified_Date(sub_categoriesDTO.getModified_Date());
            sub_categories.setCreated_Date(sub_categoriesDTO.getCreated_Date());
            sub_categories.setSub_CategoryID(sub_categoriesDTO.getSub_CategoryID());
            sub_categories.setSub_CategoryName(sub_categoriesDTO.getSub_CategoryName());
            sub_categories.setCategories(convertToModel(categoriesDTO));
            subCategorieRepository.save(sub_categories);
        }
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
            sub_categoriesDTO.setSub_CategoryName(sub_categories.getSub_CategoryName());
            sub_categoriesDTO.setCreated_Date(sub_categories.getCreated_Date());
            sub_categoriesDTO.setModified_Date(sub_categories.getModified_Date());
            listSub.add(sub_categoriesDTO);
        }
        categoriesDTO.setSub_categoriesDTO(listSub);
        return categoriesDTO;
    }

    public Categories convertToModel(CategoriesDTO categoriesDTO){
        Categories categories = new Categories();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (categoriesDTO.getCategoryID() != 0){
            categoriesDTO.setModified_Date(SystemUtils.convertToDate(timestamp));
        }
        else{
            categoriesDTO.setCreated_Date(SystemUtils.convertToDate(timestamp));
        }
        categories.setCategoryID(categoriesDTO.getCategoryID());
        categories.setCategoryName(categoriesDTO.getCategoryName());
        categories.setCreated_Date(categoriesDTO.getCreated_Date());
        categories.setModified_Date(categoriesDTO.getModified_Date());
        return categories;
    }

    public int CHECK_PATTERN(CategoriesDTO categoriesDTO){
        int count = 0;
        for (Sub_CategoriesDTO sub_categoriesDTO:categoriesDTO.getSub_categoriesDTO()){
            if (SystemUtils.PATTERN_SPECIAL_CHARACTER_NUMBERS(sub_categoriesDTO.getSub_CategoryName())){
                count += 0;
            }else{
                count++;
                break;
            }
        }
        return count;
    }

}
