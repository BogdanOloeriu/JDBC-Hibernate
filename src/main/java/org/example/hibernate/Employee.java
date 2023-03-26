package org.example.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

// o adnotam ca asta este o clasa ca o oglinda a unui tabel si sa creeze hibernate ul in DB, clasele astea se numesc entitati
@Entity
public class Employee {

    @Id // cu adnotarea asta ii zicem ca id va fi primary key
    private Integer id;

    private String name;

    private String occupation;
    private Date birthdate;


}
