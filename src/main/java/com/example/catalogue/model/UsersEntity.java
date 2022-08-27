package com.example.catalogue.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UsersEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String username;

    @NotBlank
    @Column
    private String password;

    // @ManyToMany(fetch = FetchType.LAZY)
    // @JoinTable(
    //         name = "user_to_roles",
    //         joinColumns = {@JoinColumn(name = "user_id")},
    //         inverseJoinColumns = {@JoinColumn(name = "role_id")}
    // )
    // private List<RolesEntity> roles = new ArrayList<>();

    public UsersEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsersEntity() {
    }

    public UsersEntity(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    public Long getId() {
        return this.id;
    }

    public @NotBlank String getUsername() {
        return this.username;
    }

    public @NotBlank String getPassword() {
        return this.password;
    }

    // public List<RolesEntity> getRoles() {
    //     return this.roles;
    // }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(@NotBlank String name) {
        this.username = name;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    // public void setRoles(List<RolesEntity> roles) {
    //     this.roles = roles;
    // }
}
