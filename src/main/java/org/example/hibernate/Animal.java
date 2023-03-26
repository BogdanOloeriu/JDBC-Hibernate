package org.example.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Animal {

    @Id
    private Integer id;
    private String species;
    private String name;
    private Integer yearOfBirth;
}
