package it.unibz.taskcalendarservice.common.application;

import it.unibz.taskcalendarservice.common.domain.place.CRUDPlace;
import it.unibz.taskcalendarservice.common.domain.place.CreatePlaceDTO;
import it.unibz.taskcalendarservice.common.domain.place.Place;
import it.unibz.taskcalendarservice.common.domain.place.SearchPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/place")

public class PlaceController {

    private final CRUDPlace crudPlace;
    private final SearchPlace searchPlace;

    @Autowired
    public PlaceController(CRUDPlace crudPlace, SearchPlace searchPlace) {
        this.crudPlace = crudPlace;
        this.searchPlace = searchPlace;
    }

    @PostMapping("/{id}")
    public Place findById(@PathVariable("id") Long id) {
        return searchPlace.findById(id);
    }

    @PostMapping("/create")
    public Place createPlace(@RequestBody CreatePlaceDTO createPlaceDTO) {
        return crudPlace.createPlace(createPlaceDTO.getName());
    }

    @PostMapping("/update/{id}")
    public Place updatePlace(@PathVariable("id") Long id, String name) {
        return crudPlace.updatePlace(id, name);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTask(@PathVariable("id") Long id) {
        return crudPlace.deletePlace(id);
    }

    @GetMapping("/getAll")
    public List<Place> getAll() {
        return searchPlace.getAll();
    }
}