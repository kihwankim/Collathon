package com.collathon.backendproject.controller;

import com.collathon.backendproject.entity.Student;
import com.collathon.backendproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getData")
    public List<Student> getAllStudentData() {
        return this.studentService.getAllStudentData();
    }

    @PostMapping("/insert")
    public boolean insertStudent(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "email") String email) {
        Student student = new Student(firstName, lastName, email);
        student.setId(1);
        this.studentService.insertStudentInMongoDB(student);

        return true;
    }
}
