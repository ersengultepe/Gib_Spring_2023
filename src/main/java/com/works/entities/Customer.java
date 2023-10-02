package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Column(length = 200)
    private String name;

    @Column(length = 150)
    private String email;

    private String password;
    private Integer age;

}
