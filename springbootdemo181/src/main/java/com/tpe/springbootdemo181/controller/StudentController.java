package com.tpe.springbootdemo181.controller;

import com.tpe.springbootdemo181.domain.Student;
import com.tpe.springbootdemo181.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // To create Rest API and tell Spring that we are using JSON Bodies
@RequestMapping("/students") // http://localhost:8080/students
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Get All Students
    @GetMapping
    public List<Student> getAllStudents(){

        List<Student> students = studentService.findAllStudents();

        //return new ResponseEntity<>(students, HttpStatus.OK);
        return ResponseEntity.ok(students).getBody();

    }

}