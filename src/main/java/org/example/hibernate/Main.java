package org.example.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Statement;

// daca pun crate-drop, dupa ce lucrez pe el o sa le staerga, in hibernate.cfg.xml sa pun drop, doar daca vreau

public class Main {

    public static void main(String[] args) {


        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Animal.class)
                .buildSessionFactory();

        // WRITE CODE


        sessionFactory.close();

// nu este nevoie sa mai fac o sesiune, pot sa adaug direct in sesiune da sus
//        SessionFactory sessionFactory1 = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Animal.class)
//                .buildSessionFactory();
//
//
//
//
//        sessionFactory1.close();
    }
}
