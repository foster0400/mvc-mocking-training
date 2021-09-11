package ardi.springintro.model;

public class Species {
    private String name;
    private String classification;

    public Species() {
    }

    public Species(String name, String classification) {
        this.name = name;
        this.classification = classification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
