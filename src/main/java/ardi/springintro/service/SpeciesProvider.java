package ardi.springintro.service;

import ardi.springintro.model.Species;

import java.util.List;

public interface SpeciesProvider {
    public List<Species> getSpecies();

    public Species getaSpecies(int index);
}
