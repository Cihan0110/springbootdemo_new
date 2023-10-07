package com.tpe.springbootdemo181.controller;

import com.tpe.springbootdemo181.domain.Student;
import com.tpe.springbootdemo181.dto.StudentDTO;
import com.tpe.springbootdemo181.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // To create Rest API and tell Spring that we are using JSON Bodies
@RequestMapping("/students") // http://localhost:8080/students
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Get All Students
    @GetMapping // http://localhost:8080/students + GET
    public ResponseEntity<List<Student>> getAllStudents(){

        List<Student> students = studentService.findAllStudents();

        //return new ResponseEntity<>(students, HttpStatus.OK);
        return ResponseEntity.ok(students); // HttpStatus.OK

    }

    // Save a Student
    @PostMapping // http://localhost:8080/students + POST
    public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student){
        /*
            message: Student has been created successfully
            status: true
        */
        studentService.saveStudent(student);


        Map<String, String> map = new HashMap<>();
        map.put("message", "Student has been created successfully.");
        map.put("status", "true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    // Get a Student By id
    @GetMapping("/query") // http://localhost:8080/students/query?id=2 + GET
    public ResponseEntity<Student> getStudentByIdRequestParam(@RequestParam("id") Long id){

        Student student = studentService.getStudentById(id);

        return new ResponseEntity<>(student, HttpStatus.OK); // 200

    }

    // Get a Student By id
    @GetMapping("/{id}") // http://localhost:8080/students/2 + GET
    public ResponseEntity<Student> getStudentByIdPathVariable(@PathVariable Long id){

        Student student = studentService.getStudentById(id);

        return ResponseEntity.ok(student); // 200

    }

    // Delete a Student By id
    @DeleteMapping("/{id}") // http://localhost:8080/students/2 + DELETE
    public ResponseEntity<Map<String, String>> deleteStudentById(@PathVariable("id") Long id){

        studentService.deleteStudent(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student has been deleted successfully.");
        map.put("status", "true");

        //return new ResponseEntity<>(map, HttpStatus.OK);
        return ResponseEntity.ok(map); // 200

    }

    // Update a Student By id
    @PutMapping("/{id}") // http://localhost:8080/students/2 + PUT
    public ResponseEntity<Map<String, String>> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO){

        studentService.updateStudent(id, studentDTO);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student has been updated successfully.");
        map.put("status", "true");

        return ResponseEntity.ok(map);

    }

    //Getting the Students with pagination
    @GetMapping("/pagination")
    public ResponseEntity<Page<Student>>getAllStudentsWithPagination(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("direction")Sort.Direction direction
            ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));

        Page<Student> pageOfStudents = studentService.getAllStudentsWithPagination(pageable);


        return ResponseEntity.ok(pageOfStudents);
    }

    // Getting Students by their last name.
    @GetMapping("/search") // http://localhost:8080/students/search?lastname=A
    public ResponseEntity<List<Student>> getStudentsByLastName(@RequestParam("lastname") String lastName){

        List<Student> students = studentService.findStudentsByLastName(lastName);

        return ResponseEntity.ok(students);

    }

    // Get Students by their grade.
    @GetMapping("/search/{grade}") // http://localhost:8080/students/search/90
    public ResponseEntity<List<Student>> getStudentsByGrade(@PathVariable int grade){

        List<Student> students = studentService.findStudentsByGrade(grade);

        return ResponseEntity.ok(students);

    }


}
