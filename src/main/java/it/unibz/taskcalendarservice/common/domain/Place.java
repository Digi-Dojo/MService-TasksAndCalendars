package it.unibz.taskcalendarservice.common.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    public Place() {
    }

    @Autowired
    public Place(Long id, String name){
        this.id = id;
        this.name = name;
    }

}