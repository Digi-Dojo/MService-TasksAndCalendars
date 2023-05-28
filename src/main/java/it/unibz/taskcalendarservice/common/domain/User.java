package it.unibz.taskcalendarservice.common.domain;

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