package com.rossCarmack.studentOrganizer.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {


    private final StudentService studentService;

    @Autowired //dependency injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public List<Student> getAllStudents() {
        return  studentService.getAllStudents();
    }

    @PostMapping()
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }
}
