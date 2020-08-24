package com.nhatduy.parloweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "sub_categories")
public class Sub_Categories {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sub_CategoryID")
    private int sub_CategoryID;

    @Column(name = "Sub_CategoryName")
    private String sub_categoryName;

    @Column(name = "Created_Date")
    private String created_Date;

    @Column(name = "Modified_Date")
    private String modified_Date;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "CategoryID")
    private Categories categories;

    public int getSub_CategoryID() {
        return sub_CategoryID;
    }

    public void setSub_CategoryID(int sub_CategoryID) {
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

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
}
