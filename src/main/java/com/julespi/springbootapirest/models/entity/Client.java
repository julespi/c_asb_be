package com.julespi.springbootapirest.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private String name;

    @Getter
    @Setter
    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date created;

    @PrePersist
    public void prePersist() {
        this.setCreated(new Date());
    }
}
