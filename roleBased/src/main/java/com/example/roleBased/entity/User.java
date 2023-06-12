package com.example.roleBased.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;
    private String username;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
    joinColumns = {
            @JoinColumn(name = "username")
    },
            inverseJoinColumns = {
            @JoinColumn(name = "role_name")
            }
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId")
    private Cart cart;

    public boolean isPresent() {
        return true;
    }
}
