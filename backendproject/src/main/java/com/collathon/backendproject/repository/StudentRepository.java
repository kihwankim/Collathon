package com.collathon.backendproject.repository;

import com.collathon.backendproject.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertStudentData(Student student) {
        this.mongoTemplate.insert(student);
    }

    public List<Student> getAllStudentData() {
        return this.mongoTemplate.findAll(Student.class);
    }

    public Student findOneStudentData(int id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return this.mongoTemplate.findOne(query, Student.class);
    }

    public void deleteStudent(Student student) {
        this.mongoTemplate.remove(student);
    }
}
