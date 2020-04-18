package com.rossCarmack.studentOrganizer.student;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Student {

    @Getter private final UUID studentId;
    @Getter private final String firstName;
    @Getter private final String lastName;
    @Getter private final String email;
    @Getter private final Gender gender;

    public Student(UUID studentId, String firstName, String lastName, String email, Gender gender) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    enum Gender {
        MALE, FEMALE
    }


}
