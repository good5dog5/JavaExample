package com.Jordan.Example.stream;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
public class Person {
    private int personId;
    private String name;
    private Date birthDate;
    private Gender gender;

    public Person(int personId, String name, Date birthDate, Gender gender) {
        super();
        this.personId = personId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public static enum Gender {
        MALE,
        FEMALE
    }
}
