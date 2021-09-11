package ardi.springintro.model;

public class SwapiStarshipResponse {
    private String name;
    private String manufacturer;

    public SwapiStarshipResponse() {
    }

    public SwapiStarshipResponse(String name, String manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
