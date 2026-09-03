package com.pashusetu.pashusetu.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "dairy_animals",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_animal_tag_number",
                        columnNames = "tag_number"
                )
        }
)
public class DairyAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_number", nullable = false, unique = true)
    private String tagNumber;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnimalType animalType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    private LocalDate dateOfBirth;

    private Double weight;

    private String color;

    private String healthStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;


    // Default constructor
    public DairyAnimal() {
    }


    // Parameterized constructor
    public DairyAnimal(
            String tagNumber,
            String name,
            AnimalType animalType,
            Breed breed,
            LocalDate dateOfBirth,
            Double weight,
            String color,
            String healthStatus,
            Farmer farmer
    ) {

        this.tagNumber = tagNumber;
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.color = color;
        this.healthStatus = healthStatus;
        this.farmer = farmer;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}