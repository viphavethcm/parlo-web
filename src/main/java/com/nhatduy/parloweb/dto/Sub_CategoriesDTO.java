package com.nhatduy.parloweb.dto;

public class Sub_CategoriesDTO {

    private Integer sub_CategoryID;
    private String sub_categoryName;
    private String created_Date;
    private String modified_Date;

    public Integer getSub_CategoryID() {
        return sub_CategoryID;
    }

    public void setSub_CategoryID(Integer sub_CategoryID) {
        this.sub_CategoryID = sub_CategoryID;
    }

    public String getSub_categoryName() {
        return sub_categoryName;
    }

    public void setSub_categoryName(String sub_categoryName) {
        this.sub_categoryName = sub_categoryName;
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
}
