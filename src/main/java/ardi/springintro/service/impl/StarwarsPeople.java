package ardi.springintro.service.impl;

import ardi.springintro.model.People;
import ardi.springintro.model.SwapiPeople;
import ardi.springintro.service.PeopleProvider;
import ardi.springintro.service.SwapiClient;

import java.util.ArrayList;
import java.util.List;

public class StarwarsPeople implements PeopleProvider {

    SwapiClient swapiClient;

    public StarwarsPeople(SwapiClient swapiClient) {
        this.swapiClient = swapiClient;
    }

    @Override
    public List<People> getPeople() {
        List<SwapiPeople> swapiPeople = swapiClient.getPeople();
        List<People> response = new ArrayList<>();

        for (SwapiPeople swapiPerson: swapiPeople) {
            People person = new People();
            person.setName(swapiPerson.getName());
            person.setGender(swapiPerson.getGender());
            response.add(person);
        }
        return response;
    }

    @Override
    public People getPeople(int index) {
        SwapiPeople swapiPeople = swapiClient.getaPeople(index);
        People response = new People();
        response.setName(swapiPeople.getName());
        response.setGender(swapiPeople.getGender());
        return response;
    }
}
