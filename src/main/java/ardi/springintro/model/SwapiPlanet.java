package ardi.springintro.model;

public class SwapiPlanet {
    private String name;
    private String diameter;

    public SwapiPlanet() {
    }

    public SwapiPlanet(String name, String diameter) {
        this.name = name;
        this.diameter = diameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }
}
