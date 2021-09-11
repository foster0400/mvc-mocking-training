package ardi.springintro.service.impl;

import ardi.springintro.model.Starship;
import ardi.springintro.model.SwapiStarship;
import ardi.springintro.service.StarshipProvider;
import ardi.springintro.service.SwapiClient;

import java.util.ArrayList;
import java.util.List;

public class StarwarsStarship implements StarshipProvider {
    SwapiClient swapiClient;

    public StarwarsStarship(SwapiClient swapiClient) {
        this.swapiClient = swapiClient;
    }

    @Override
    public List<Starship> getStarships() {
        List<SwapiStarship> swapiStarships = swapiClient.getStarships();
        List<Starship> response = new ArrayList<>();
        for (SwapiStarship swapiStarship: swapiStarships) {
            Starship starship = new Starship();
            starship.setName(swapiStarship.getName());
            starship.setManufacturer(swapiStarship.getManufacturer());
            response.add(starship);
        }
        return response;
    }

    @Override
    public Starship getStarship(int index) {
        SwapiStarship swapiStarship = swapiClient.getStarship(index);
        Starship response = new Starship();
        response.setName(swapiStarship.getName());
        response.setManufacturer(swapiStarship.getManufacturer());
        return response;
    }
}
