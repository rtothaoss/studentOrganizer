package com.rossCarmack.studentOrganizer.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Student {

    @Getter private final UUID studentId;
    @NotBlank(message = "First name cannot be blank")
    @Getter private final String firstName;
    @NotBlank(message = "Last name cannot be blank")
    @Getter private final String lastName;
    @NotBlank(message = "Email cannot be blank")
    @Getter private final String email;
    @NotNull
    @Getter private final Gender gender;

    public Student(@JsonProperty("studentId") UUID studentId,
                   @JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("email") String email,
                   @JsonProperty("gender") Gender gender) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }

    enum Gender {
        MALE, FEMALE
    }


}
