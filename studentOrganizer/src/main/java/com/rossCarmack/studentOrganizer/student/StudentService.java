package com.rossCarmack.studentOrganizer.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDataAccessService studentDataAccessService;

    @Autowired
    public StudentService(StudentDataAccessService studentDataAccessService) {
        this.studentDataAccessService = studentDataAccessService;
    }

    List<Student> getAllStudents() {
            return studentDataAccessService.selectAllStudents();
    }
    void addNewStudent( Student student) {

        addNewStudent(null, student);
    }

    void addNewStudent(UUID studentId, Student student) {
        UUID newStudentId = Optional.ofNullable(studentId).orElse(UUID.randomUUID()); //if statement for UUID

        studentDataAccessService.insertStudent(newStudentId, student);
    }
}
