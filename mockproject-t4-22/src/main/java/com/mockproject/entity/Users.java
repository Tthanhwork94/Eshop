package com.mockproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    @Size(max=20, message = "username must be less than 2 characters")
    private String username;

    @Column(name = "fullname")
    @Size(max=50, message = "fullname must be less than 50 characters")
    private String fullname;

    @Column(name = "hashPassword")
    private String hashPassword;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "createdDate")
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "imgUrl")
    private String imgUrl;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @OneToOne
    @JoinColumn(name = "roleId",referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
    private Roles roles;

}
