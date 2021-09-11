package ardi.springintro.service.impl;

import ardi.springintro.model.*;
import ardi.springintro.service.SwapiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;

@Component
public class SwapiClientImpl implements SwapiClient {

  @Autowired
  @Qualifier("swapiWebClient")
  private WebClient swapiWebClient;


//  public static <T>SwapiResponse<T> getResp(T x, WebClient webClient) {
//
//    return response;
//  }

  //============================FILM=============================
  @Override
  public List<SwapiFilm> getFilms() {
    SwapiResponse<SwapiFilm> response = swapiWebClient.get()
            .uri("/films")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiFilm>>() {})
            .block();
    return response.getResults();
  }

  @Override
  public SwapiFilm getFilm(int index) {
    SwapiFilmResponse response = swapiWebClient.get()
            .uri("/films/"+index)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiFilmResponse>() {})
            .block();
    SwapiFilm swapiFilm = new SwapiFilm(response.getTitle(),response.getEpisode_id());
    return swapiFilm;
  }


  //============================PEOPLE=============================
  @Override
  public List<SwapiPeople> getPeople() {
    SwapiResponse<SwapiPeople> response = swapiWebClient.get()
            .uri("/people")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiPeople>>() {})
            .block();
    return response.getResults();
  }

  @Override
  public SwapiPeople getaPeople(int index) {
    SwapiPeopleResponse response = swapiWebClient.get()
            .uri("/people/"+index)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiPeopleResponse>() {})
            .block();
    SwapiPeople swapiPeople = new SwapiPeople(response.getName(),response.getGender());
    return swapiPeople;
  }


  //============================Planet=============================
  @Override
  public List<SwapiPlanet> getPlanets() {
    SwapiResponse<SwapiPlanet> response= swapiWebClient.get()
            .uri("/planets")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiPlanet>>() {})
            .block();
    return response.getResults();
  }


  @Override
  public SwapiPlanet getPlanet(int index) {
    SwapiPlanetResponse response = swapiWebClient.get()
            .uri("/planets/"+index)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiPlanetResponse>() {})
            .block();

    return new SwapiPlanet(response.getName(),response.getDiameter());
  }



  //============================Species=============================
  @Override
  public List<SwapiSpecies> getSpecies() {
    SwapiResponse<SwapiSpecies> response = swapiWebClient.get()
            .uri("/species")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiSpecies>>() {})
            .block();

    return response.getResults();
  }

  @Override
  public SwapiSpecies getaSpecies(int index) {
    SwapiSpeciesResponse response = swapiWebClient.get()
            .uri("/species/"+index)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiSpeciesResponse>() {})
            .block();
    return new SwapiSpecies(response.getName(),response.getClassification());
  }


  //============================Vehicles=============================
  @Override
  public List<SwapiVehicle> getVehicles() {
    SwapiResponse<SwapiVehicle> response = swapiWebClient.get()
            .uri("/vehicles")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiVehicle>>() {})
            .block();

    return response.getResults();
  }

  @Override
  public SwapiVehicle getVehicle(int index) {
    SwapiVehicle response = swapiWebClient.get()
            .uri("/vehicles/"+ index)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiVehicle>() {})
            .block();
    return new SwapiVehicle(response.getName(),response.getManufacturer());
  }



  //============================Starship=============================


  @Override
  public List<SwapiStarship> getStarships() {
    SwapiResponse<SwapiStarship> response = swapiWebClient.get()
            .uri("/starships")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiStarship>>() {})
            .block();
    return response.getResults();
  }

  @Override
  public SwapiStarship getStarship(int index) {
    SwapiStarshipResponse response = swapiWebClient.get()
            .uri("/starships/"+ index)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<SwapiStarshipResponse>() {})
            .block();

    return new SwapiStarship(response.getName(),response.getManufacturer());
  }
}
