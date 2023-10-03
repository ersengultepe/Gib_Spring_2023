package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Size(min = 2, max = 200)
    @NotEmpty
    @NotNull
    @Column(length = 200)
    private String name;

    @Email
    @Size(min = 8, max = 150)
    @NotEmpty
    @NotNull
    @Column(length = 150)
    private String email;

    @Size(min = 4, max = 500)
    @NotEmpty
    @NotNull
    private String password;

    @Max(70)
    @Min(18)
    @NotNull
    private Integer age;

}
