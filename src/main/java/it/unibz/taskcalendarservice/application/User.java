package it.unibz.taskcalendarservice.application;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    public User() {
    }

    public User(Long id, String name){
        this.id = id;
        this.name = name;
    }

}