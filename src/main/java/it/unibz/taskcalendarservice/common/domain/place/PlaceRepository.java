package it.unibz.taskcalendarservice.common.domain.place;

import it.unibz.taskcalendarservice.common.domain.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
