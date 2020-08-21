package com.nhatduy.parloweb.dto;

import java.util.List;

public class UserDTO {
    private Integer userID;
    private String userName;
    private String password;
    private boolean active;
    private String created_Date;
    private String modified_Date;
    private List<RoleDTO> roles;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
