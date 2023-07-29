package us.jameschan.springboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.beans.JavaBean;

@JavaBean
@Entity
@Table(name = "user")
public final class User {
    @Id
    @Column(nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @Column(name = "auth_string", nullable = false, length = 80)
    private String authString;
}
