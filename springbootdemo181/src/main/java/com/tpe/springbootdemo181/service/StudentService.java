package com.tpe.springbootdemo181.service;

import com.tpe.springbootdemo181.domain.Student;
import com.tpe.springbootdemo181.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public List<Student> findAllStudents() {

        List<Student> students = studentRepository.findAll();

        return students;
    }
}
