package com.collathon.backendproject.service;

import com.collathon.backendproject.entity.Student;
import com.collathon.backendproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void insertStudentInMongoDB(Student student) {
        this.studentRepository.insertStudentData(student);
    }

    public List<Student> getAllStudentData() {
        return this.studentRepository.getAllStudentData();
    }
}
