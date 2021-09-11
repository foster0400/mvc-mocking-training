package ardi.springintro.service.impl;

import ardi.springintro.model.Movie;
import ardi.springintro.model.SwapiFilm;
import ardi.springintro.service.MovieProvider;
import ardi.springintro.service.SwapiClient;

import java.util.ArrayList;
import java.util.List;

public class StarwarsMovie implements MovieProvider {

    List<Movie> movies = new ArrayList<>();
    /*
    *  return daftar movie starwars
    *
    * */

    SwapiClient swapiClient;

    public StarwarsMovie(SwapiClient swapiClient) {
        this.swapiClient = swapiClient;
    }

    @Override
    public List<Movie> getMovies(){
        List<SwapiFilm> swapiFilms = swapiClient.getFilms();
        List<Movie> response = new ArrayList<>();
//        for (int i = 0; i < swapiFilms.size(); i++) {
//            SwapiFilm swapiFilm = swapiFilms.get(i);
//        }
        for (SwapiFilm swapiFilm: swapiFilms) {
            Movie movie = new Movie();
            movie.setEpisode(swapiFilm.getEpisode_id());
            movie.setJudul(swapiFilm.getTitle());

            response.add(movie);
        }

        return response;
    }

    @Override
    public Movie getMovie(int index) {
        SwapiFilm swapiFilm = swapiClient.getFilm(index);
        Movie response = new Movie();
        response.setJudul(swapiFilm.getTitle());
        response.setEpisode(swapiFilm.getEpisode_id());

        return response;
    }

    @Override
    public boolean saveMovie(Movie movie) {
        movies.add(movie);
        return true;
    }

    @Override
    public boolean deleteMovie() {
        movies.clear();
        return true;
    }
}
