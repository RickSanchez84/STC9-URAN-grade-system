package ru.innopolis.stc9.db.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class Speciality {
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;

    public Speciality() {
    }

    public Speciality(String name) {
        this.name = name;
    }

    @Id
    @SequenceGenerator(name = "specialitySeq", sequenceName = "SPECIALITY_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialitySeq")
    public long getId() {
        return id;
    }
}
