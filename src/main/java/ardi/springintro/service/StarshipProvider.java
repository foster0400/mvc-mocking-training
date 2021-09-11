package ardi.springintro.service;



import ardi.springintro.model.Starship;

import java.util.List;

public interface StarshipProvider {
    public List<Starship> getStarships();

    public Starship getStarship(int index);
}
