package ardi.springintro.service;

import ardi.springintro.model.*;

import java.util.List;

public interface SwapiClient {
  List<SwapiFilm> getFilms();
  SwapiFilm getFilm(int index);

  List<SwapiPeople> getPeople();
  SwapiPeople getaPeople(int index);

  List<SwapiPlanet> getPlanets();
  SwapiPlanet getPlanet(int index);

  List<SwapiSpecies> getSpecies();
  SwapiSpecies getaSpecies(int index);

  List<SwapiVehicle> getVehicles();
  SwapiVehicle getVehicle(int index);

  List<SwapiStarship> getStarships();
  SwapiStarship getStarship(int index);
}
