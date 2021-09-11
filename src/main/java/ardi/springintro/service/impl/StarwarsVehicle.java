package ardi.springintro.service.impl;

import ardi.springintro.model.SwapiVehicle;
import ardi.springintro.model.Vehicle;
import ardi.springintro.service.SwapiClient;
import ardi.springintro.service.VehicleProvider;

import java.util.ArrayList;
import java.util.List;

public class StarwarsVehicle implements VehicleProvider {

    SwapiClient swapiClient;

    public StarwarsVehicle(SwapiClient swapiClient) {
        this.swapiClient = swapiClient;
    }

    @Override
    public List<Vehicle> getVehicles() {
        List<SwapiVehicle> swapiVehicles = swapiClient.getVehicles();
        List<Vehicle> response = new ArrayList<>();
        for (SwapiVehicle swapiVehicle: swapiVehicles) {
            Vehicle vehicle = new Vehicle();
            vehicle.setName(swapiVehicle.getName());
            vehicle.setManufacturer(swapiVehicle.getManufacturer());
            response.add(vehicle);
        }
        return response;
    }

    @Override
    public Vehicle getVehicle(int index) {
        SwapiVehicle swapiVehicle = swapiClient.getVehicle(index);
        Vehicle response = new Vehicle();
        response.setName(swapiVehicle.getName());
        response.setManufacturer(swapiVehicle.getManufacturer());
        return response;
    }
}
