package com.rossCarmack.studentOrganizer.student;

import com.rossCarmack.studentOrganizer.EmailValidator;
import com.rossCarmack.studentOrganizer.exception.ApiRequestException;
import com.rossCarmack.studentOrganizer.exception.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDataAccessService studentDataAccessService;
    private final EmailValidator emailValidator;

    @Autowired
    public StudentService(StudentDataAccessService studentDataAccessService, EmailValidator emailValidator) {
        this.studentDataAccessService = studentDataAccessService;
        this.emailValidator = emailValidator;
    }

    List<Student> getAllStudents() {
            return studentDataAccessService.selectAllStudents();
    }
    void addNewStudent( Student student) {

        addNewStudent(null, student);
    }

    void addNewStudent(UUID studentId, Student student) {
        UUID newStudentId = Optional.ofNullable(studentId).orElse(UUID.randomUUID()); //if statement for UUID

        if(!emailValidator.test(student.getEmail())) {
            throw new ApiRequestException(student.getEmail() + " is not valid");
        }

        if(studentDataAccessService.isEmailTaken(student.getEmail())) {
            throw new EmailAlreadyExistsException("Email '" + student.getEmail() + "' already exists.");
        }


        studentDataAccessService.insertStudent(newStudentId, student);
    }

    List<StudentCourse> getAllCoursesForStudent(UUID studentId) {
        return studentDataAccessService.selectAllStudentCourse(studentId);
    }
}
