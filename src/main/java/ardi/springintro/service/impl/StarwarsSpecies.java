package ardi.springintro.service.impl;

import ardi.springintro.model.Species;
import ardi.springintro.model.SwapiSpecies;
import ardi.springintro.service.SpeciesProvider;
import ardi.springintro.service.SwapiClient;

import java.util.ArrayList;
import java.util.List;

public class StarwarsSpecies implements SpeciesProvider {

    SwapiClient swapiClient;

    public StarwarsSpecies(SwapiClient swapiClient) {
        this.swapiClient = swapiClient;
    }

    @Override
    public List<Species> getSpecies() {
        List<SwapiSpecies> swapiSpecies = swapiClient.getSpecies();
        List<Species> response = new ArrayList<>();
        for (SwapiSpecies aSwapiSpecies: swapiSpecies) {
            Species species = new Species();
            species.setName(aSwapiSpecies.getName());
            species.setClassification(aSwapiSpecies.getClassification());
            response.add(species);
        }
        return response;
    }

    @Override
    public Species getaSpecies(int index) {
        SwapiSpecies swapiSpecies = swapiClient.getaSpecies(index);
        Species response = new Species();
        response.setName(swapiSpecies.getName());
        response.setClassification(swapiSpecies.getClassification());
        return response;
    }
}
