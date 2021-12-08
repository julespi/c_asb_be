package com.julespi.springbootapirest.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 4)
    private String name;

    @Getter
    @Setter
    @Column(name = "last_name", nullable = false)
    @NotEmpty

    private String last_name;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date created;

    @Getter
    @Setter
    private String profile_picture;
}
