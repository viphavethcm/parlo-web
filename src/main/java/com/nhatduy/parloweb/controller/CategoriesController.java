package com.nhatduy.parloweb.controller;

import com.nhatduy.parloweb.constants.SystemConstants;
import com.nhatduy.parloweb.dto.CategoriesDTO;
import com.nhatduy.parloweb.entity.Categories;
import com.nhatduy.parloweb.entity.StatusResponse;
import com.nhatduy.parloweb.service.CategoriesService;
import com.nhatduy.parloweb.utils.SystemUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/categories")// Add new Categories
    @ApiOperation(value = "Add new Categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 502,message = "Something went wrong")
    })
    public ResponseEntity<?> addCategories(@RequestBody CategoriesDTO categoriesDTO){
        ResponseEntity responseEntity = null;
        if (SystemUtils.PATTERN_SPECIAL_CHARACTER_NUMBERS(categoriesDTO.getTitle())){
            categoriesDTO.setID(0);
            categoriesService.save(categoriesDTO);
            responseEntity = new ResponseEntity(
                    new StatusResponse(SystemConstants.MESSAGE_200,System.currentTimeMillis()),HttpStatus.OK);

        }
        else {
            responseEntity = new ResponseEntity(
                    new StatusResponse(SystemConstants.MESSAGE_502,System.currentTimeMillis()),HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/categories")// Update Categories
    @ApiOperation(value = "Update Categories")
    public ResponseEntity<?> updateCategories(@RequestBody CategoriesDTO categoriesDTO){
        ResponseEntity responseEntity = null;
        if (SystemUtils.PATTERN_SPECIAL_CHARACTER_NUMBERS(categoriesDTO.getTitle())){
            categoriesService.save(categoriesDTO);
            responseEntity = new ResponseEntity(
                    new StatusResponse(SystemConstants.MESSAGE_200,System.currentTimeMillis()),HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity(
                    new StatusResponse(SystemConstants.MESSAGE_502,System.currentTimeMillis()),HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }



}
