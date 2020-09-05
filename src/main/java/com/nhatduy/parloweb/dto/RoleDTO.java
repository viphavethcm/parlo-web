package com.nhatduy.parloweb.dto;

import com.nhatduy.parloweb.entity.Role;

public class RoleDTO {
    private Integer roleID;
    private String roleName;

    public RoleDTO(Integer roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }
    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
