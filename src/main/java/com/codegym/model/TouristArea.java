package com.codegym.model;

import javax.persistence.*;

@Entity
public class TouristArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double population;
    @ManyToOne
    private Province province;
    @ManyToOne
    private TypesOfTourism typesOfTourism;
    private String image;

    public TouristArea(Long id, String name, double population, Province province, TypesOfTourism typesOfTourism, String image) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.province = province;
        this.typesOfTourism = typesOfTourism;
        this.image = image;
    }

    public TouristArea() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public TypesOfTourism getTypesOfTourism() {
        return typesOfTourism;
    }

    public void setTypesOfTourism(TypesOfTourism typesOfTourism) {
        this.typesOfTourism = typesOfTourism;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
