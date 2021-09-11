package ardi.springintro.service;

import ardi.springintro.model.People;

import java.util.List;

public interface PeopleProvider {
    public List<People> getPeople();

    public People getPeople(int index);
}
