package com.fofancy.auth.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 14.07.2017.
 */
@Entity
@Table(name = "Users")
@NamedQueries({
        @NamedQuery(
                name = User.FIND_ALL,
                query = "SELECT DISTINCT u FROM User u INNER JOIN u.roles r WHERE u.username = :username"
        )
})
public class User implements Serializable {
    public final static String FIND_ALL = "User.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_generator")
    @SequenceGenerator(name = "users_seq_generator", sequenceName = "users_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_username")
    @NotNull
    private String username;
    @Column(name = "user_email")
    @NotNull
    private String email;
    @Column(name = "user_password")
    @NotNull
    private String encodedPassword;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    private List<RoleEntity> roles;

    @OneToOne(mappedBy = "user")
    private TokenEntity token;


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public TokenEntity getToken() {
        return token;
    }

    public void setToken(TokenEntity token) {
        this.token = token;
    }
}
