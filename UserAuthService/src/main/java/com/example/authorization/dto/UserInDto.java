package com.example.authorization.dto;

import com.example.authorization.model.RolesEntity;
import lombok.Builder;

import java.util.List;

@Builder
public class UserInDto {

    private String username;
    private String password;
    // private List<RolesEntity> roles;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    // public List<RolesEntity> getRoles() {
    //     return roles;
    // }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public void setRoles(List<RolesEntity> roles) {
    //     this.roles = roles;
    // }


    // public String toString() {
    //     return "UserInDto(userName=" + this.getUserName() + ", password=" + this.getPassword() + ", roles=" + this.getRoles() + ")";
    // }
}
