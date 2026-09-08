package com.pashusetu.pashusetu.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Animal whose medical history this record belongs to
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private DairyAnimal animal;

    // Veterinarian who examined the animal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinarian_id", nullable = false)
    private Veterinarian veterinarian;

    @Column(nullable = false)
    private LocalDate visitDate;

    private String symptoms;

    private String diagnosis;

    private String treatment;

    private String medicines;

    private String notes;

    // When the animal should be checked again
    private LocalDate followUpDate;


    public MedicalRecord() {
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


    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }


    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }


    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }


    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }


    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(LocalDate followUpDate) {
        this.followUpDate = followUpDate;
    }
}