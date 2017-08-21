package com.fofancy.auth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Roles")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "roles_seq_gen")
    @SequenceGenerator(
            sequenceName = "roles_seq",
            name = "roles_seq_gen",
            allocationSize = 1)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    @NotNull
    private String roleName;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    )
    private List<User> users;
}
