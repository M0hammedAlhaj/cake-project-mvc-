package com.spring.ecmmvc.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

/**
 * Role represents a user role in the system, which implements the {@link GrantedAuthority} interface
 * for Spring Security authorization purposes.
 */
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int roleId;

    @Column(name = "authorize")
    private String name;

    /**
     * Returns the authority (role name) associated with this role.
     *
     * @return The name of the role.
     */
    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }
}