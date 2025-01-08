package com.spring.ecmmvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * User represents a user entity in the system, which is mapped to the "users" table in the database.
 * It includes attributes such as first name, last name, email, password, cart, and roles.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;

    @Column(nullable = false)
    public String firstName;

    public String lastName;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "idCart")
    public Cart cart;

    @Email
    private String email;

    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_role", // Join table name
            joinColumns = @JoinColumn(name = "id_user"), // Foreign key to user
            inverseJoinColumns = @JoinColumn(name = "id_role") // Foreign key to role
    )
    private List<Role> roles;

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The first name to set for the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The last name to set for the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public @Email String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email to set for the user.
     */
    public void setEmail(@Email String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public @NotNull String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to set for the user.
     */
    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    /**
     * Gets the roles associated with the user.
     *
     * @return A list of roles associated with the user.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles for the user.
     *
     * @param roles The list of roles to set for the user.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}