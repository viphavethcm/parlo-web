package com.nhatduy.parloweb.dto;

public class CategoriesDTO {

    private Integer ID;
    private Integer parentID;
    private String title;

    public CategoriesDTO(Integer ID, Integer parentID, String title) {
        this.ID = ID;
        this.parentID = parentID;
        this.title = title;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
