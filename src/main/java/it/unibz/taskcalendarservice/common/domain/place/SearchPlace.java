package it.unibz.taskcalendarservice.common.domain.place;

import it.unibz.taskcalendarservice.common.domain.place.Place;
import it.unibz.taskcalendarservice.common.domain.place.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchPlace {
    PlaceRepository placeRepository;

    @Autowired
    public SearchPlace(PlaceRepository placeRepository){
        this.placeRepository = placeRepository;
    }

    public Place findById(Long id){
        Optional<Place> place = placeRepository.findById(id);
        if (place.isEmpty()) throw new IllegalArgumentException("Place with id '" + id + "' does not exist");
        return place.get();
    }

    public List<Place> getAll(){
        List<Place> places = placeRepository.findAll();
        System.out.println("\nPlaceList size: " + places.size());
        return places;
    }
}