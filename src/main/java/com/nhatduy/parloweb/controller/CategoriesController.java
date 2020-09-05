package com.nhatduy.parloweb.controller;

import com.nhatduy.parloweb.constants.SystemConstants;
import com.nhatduy.parloweb.dto.CategoriesDTO;
import com.nhatduy.parloweb.entity.Categories;
import com.nhatduy.parloweb.entity.StatusResponse;
import com.nhatduy.parloweb.exception.BadRequestException;
import com.nhatduy.parloweb.service.CategoriesService;
import com.nhatduy.parloweb.utils.SystemUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(description = "Categories on Menu")
@RequestMapping("/v2")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/categories") //Get all Categories
    @ApiOperation(value = "Get Menu Categories",
                response = Categories.class)
    public ResponseEntity<?> getList(){
        return new ResponseEntity<>(categoriesService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/categories")// Add new Categories
    @ApiOperation(value = "Add new Categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 400,message = "Something went wrong"),
    })
    public ResponseEntity<?> addCategories(@RequestBody CategoriesDTO categoriesDTO){
        if (SystemUtils.PATTERN_SPECIAL_CHARACTER_NUMBERS(categoriesDTO.getTitle())){
            categoriesDTO.setID(0);
            categoriesService.save(categoriesDTO);
            return new ResponseEntity<>(
                    new StatusResponse(SystemConstants.MESSAGE_200,System.currentTimeMillis()),HttpStatus.OK);
        }
        else
            throw new BadRequestException(SystemConstants.MESSAGE_400);
    }

    @PutMapping("/categories")// Update Categories
    @ApiOperation(value = "Update Categories")
    public ResponseEntity<?> updateCategories(@RequestBody CategoriesDTO categoriesDTO){
        if (SystemUtils.PATTERN_SPECIAL_CHARACTER_NUMBERS(categoriesDTO.getTitle())){
            categoriesService.save(categoriesDTO);
            return new ResponseEntity<>(
                    new StatusResponse(SystemConstants.MESSAGE_200,System.currentTimeMillis()),HttpStatus.OK);
        }else
            throw new BadRequestException(SystemConstants.MESSAGE_400);
    }

}
