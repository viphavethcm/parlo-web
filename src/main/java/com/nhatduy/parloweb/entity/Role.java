package com.nhatduy.parloweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_Id")
    private int roleID;

    @Column(name = "RoleName")
    private String roleName;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE,CascadeType.REFRESH})
    private List<User> users;

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

