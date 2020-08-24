package com.nhatduy.parloweb.controller;

import com.nhatduy.parloweb.dto.CategoriesDTO;
import com.nhatduy.parloweb.entity.Categories;
import com.nhatduy.parloweb.service.CategoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "Categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/categories")
    @ApiOperation(value = "Get Menu Categories",
                response = Categories.class)
    public List<CategoriesDTO> getList(){
        return categoriesService.findAll();
    }

}
