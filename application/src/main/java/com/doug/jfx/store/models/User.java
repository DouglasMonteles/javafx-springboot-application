package com.doug.jfx.store.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    //@Column(columnDefinition = "DEFAULT 1")
    private boolean isActive = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_user_roles",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, unique = true)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, unique = true)}
    )
    private final List<Role> roles = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tb_phone")
    @Column(name = "phone", length = 20)
    private final Set<String> phones = new HashSet<>();

    public boolean hasAuthority(String authority) {
        return this.roles
                .stream()
                .anyMatch(role -> role.getAuthority().equals(authority));
    }

}
