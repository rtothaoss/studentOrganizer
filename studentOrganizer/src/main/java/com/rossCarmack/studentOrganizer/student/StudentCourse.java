package com.rossCarmack.studentOrganizer.student;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

public class StudentCourse {

    @Getter
    private final UUID student_id;
    @Getter
    private final UUID course_id;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final String department;
    @Getter
    private final String teacherName;
    @Getter
    private final LocalDate start_date;
    @Getter
    private final LocalDate end_date;
    @Getter
    private final Integer grade;

    public StudentCourse(UUID student_id, UUID course_id, String name, String description, String department, String teacherName, LocalDate start_date, LocalDate end_date, Integer grade) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.name = name;
        this.description = description;
        this.department = department;
        this.teacherName = teacherName;
        this.start_date = start_date;
        this.end_date = end_date;
        this.grade = grade;
    }
}
