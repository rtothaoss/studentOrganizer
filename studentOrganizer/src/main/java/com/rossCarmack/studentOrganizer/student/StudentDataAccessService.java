package com.rossCarmack.studentOrganizer.student;

import com.rossCarmack.studentOrganizer.exception.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    int insertStudent(UUID newStudentId, Student student) {
//        if(isEmailTaken(student.getEmail())) {
//            throw new EmailAlreadyExistsException("Email '" + student.getEmail() + "' already exists.");
//        }

        String sql = "" +
                "INSERT INTO student (student_id, first_name, last_name, email, gender) " +
                "VALUES (?, ?, ?, ?, ?::gender)"; //the :: is for the casting in the migration table for gender enum
        return jdbcTemplate.update(sql, newStudentId, student.getFirstName(), student.getLastName(), student.getEmail(), student.getGender().name().toUpperCase());

    }

    @SuppressWarnings("ConstantConditions")
    boolean isEmailTaken(String email) {
        String sql = "" +
                    " SELECT EXISTS ( " +
                    " SELECT 1 " +
                    " FROM student " +
                    " WHERE email = ?" +
                    ")";
        return jdbcTemplate.queryForObject(sql, new Object[] {email}, (resultSet, i) -> resultSet.getBoolean(1));
    }

     List<Student> selectAllStudents() {
        String sql = "" +
                "SELECT " +
                " student_id, " +
                " first_name, " +
                " last_name, " +
                " email, " +
                " gender " +
                "FROM student";

        List<Student> students = jdbcTemplate.query(sql, mapStudentFromDb());
            return students;
    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            String studentIdStr = resultSet.getString("student_id");
            UUID studentId = UUID.fromString(studentIdStr);
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String genderStr = resultSet.getString("gender").toUpperCase();
            Student.Gender gender = Student.Gender.valueOf(genderStr);
            return new Student(
                    studentId,
                    firstName,
                    lastName,
                    email,
                    gender
            );
        };
    }


}
