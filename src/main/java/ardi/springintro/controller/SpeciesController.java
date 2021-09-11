package ardi.springintro.controller;


import ardi.springintro.model.Species;
import ardi.springintro.service.SpeciesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpeciesController {

    @Autowired
    private SpeciesProvider starwarsSpecies;

    @GetMapping("/species/{index}")
    public Species getSpecies(@PathVariable(name = "index") int urutan) {
        Species planet = starwarsSpecies.getaSpecies(urutan);
        return planet;
    }

    @GetMapping("/species")
    public List<Species> getSpecies() {
        List<Species> species = starwarsSpecies.getSpecies();
        return species;
    }


}
