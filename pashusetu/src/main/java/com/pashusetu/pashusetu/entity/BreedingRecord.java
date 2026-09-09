package com.pashusetu.pashusetu.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "breeding_records")
public class BreedingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private DairyAnimal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinarian_id", nullable = false)
    private Veterinarian veterinarian;

    @Column(nullable = false)
    private LocalDate serviceDate;

    private String breedingMethod;

    private LocalDate pregnancyCheckDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BreedingStatus breedingStatus;

    private LocalDate expectedCalvingDate;

    private LocalDate actualCalvingDate;

    private String notes;

    public BreedingRecord() {
    }

    public Long getId() {
        return id;
    }

    public DairyAnimal getAnimal() {
        return animal;
    }

    public void setAnimal(DairyAnimal animal) {
        this.animal = animal;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        this.veterinarian = veterinarian;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getBreedingMethod() {
        return breedingMethod;
    }

    public void setBreedingMethod(String breedingMethod) {
        this.breedingMethod = breedingMethod;
    }

    public LocalDate getPregnancyCheckDate() {
        return pregnancyCheckDate;
    }

    public void setPregnancyCheckDate(LocalDate pregnancyCheckDate) {
        this.pregnancyCheckDate = pregnancyCheckDate;
    }

    public BreedingStatus getBreedingStatus() {
        return breedingStatus;
    }

    public void setBreedingStatus(BreedingStatus breedingStatus) {
        this.breedingStatus = breedingStatus;
    }

    public LocalDate getExpectedCalvingDate() {
        return expectedCalvingDate;
    }

    public void setExpectedCalvingDate(LocalDate expectedCalvingDate) {
        this.expectedCalvingDate = expectedCalvingDate;
    }

    public LocalDate getActualCalvingDate() {
        return actualCalvingDate;
    }

    public void setActualCalvingDate(LocalDate actualCalvingDate) {
        this.actualCalvingDate = actualCalvingDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}