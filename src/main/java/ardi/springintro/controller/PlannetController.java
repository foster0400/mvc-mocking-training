package ardi.springintro.controller;


import ardi.springintro.model.Planet;
import ardi.springintro.service.PlanetProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlannetController {

    @Autowired
    private PlanetProvider starwarsPlanet;

    @GetMapping("/planets/{index}")
    public Planet getPlanet(@PathVariable(name = "index") int urutan) {
        Planet planet = starwarsPlanet.getPlanet(urutan);
        return planet;
    }

    @GetMapping("/planets")
    public List<Planet> getPlanets() {
        List<Planet> planets = starwarsPlanet.getPlanets();
        return planets;
    }


}
