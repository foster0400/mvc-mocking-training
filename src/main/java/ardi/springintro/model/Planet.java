package ardi.springintro.model;

public class Planet {
    private String name;
    private String diameter;

    public Planet() {
    }

    public Planet(String name, String diameter) {
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
