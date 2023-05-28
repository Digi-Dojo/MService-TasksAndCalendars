package it.unibz.taskcalendarservice.application;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
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