package com.nhatduy.parloweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@NamedEntityGraph(name = "Categories.sub_categories",
                    attributeNodes = @NamedAttributeNode("sub_categories"))
public class Categories {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private int categoryID;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "Created_Date")
    private String created_Date;

    @Column(name = "Modified_Date")
    private String modified_Date;

    @OneToMany(mappedBy = "categories",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Sub_Categories> sub_categories;

    public void addSub_Categories(Sub_Categories sub_category){
        if (sub_categories == null){
            sub_categories = new ArrayList<>();
        }
        sub_categories.add(sub_category);
    }


    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
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

    public List<Sub_Categories> getSub_categories() {
        return sub_categories;
    }

    public void setSub_categories(List<Sub_Categories> sub_categories) {
        this.sub_categories = sub_categories;
    }
}
