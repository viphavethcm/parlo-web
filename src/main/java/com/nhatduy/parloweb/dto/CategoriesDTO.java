package com.nhatduy.parloweb.dto;

import java.util.List;

public class CategoriesDTO {

    private Integer categoryID;
    private String categoryName;
    private String created_Date;
    private String modified_Date;
    private List<Sub_CategoriesDTO> sub_categoriesDTO;

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(String created_Date) {
        this.created_Date = created_Date;
    }

    public String getModified_Date() {
        return modified_Date;
    }

    public void setModified_Date(String modified_Date) {
        this.modified_Date = modified_Date;
    }

    public List<Sub_CategoriesDTO> getSub_categoriesDTO() {
        return sub_categoriesDTO;
    }

    public void setSub_categoriesDTO(List<Sub_CategoriesDTO> sub_categoriesDTO) {
        this.sub_categoriesDTO = sub_categoriesDTO;
    }
}
