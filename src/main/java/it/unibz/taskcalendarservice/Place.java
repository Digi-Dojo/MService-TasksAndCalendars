package it.unibz.taskcalendarservice;

import javax.persistence.Entity;

@Entity
public record Place(Long id, String name) {
}