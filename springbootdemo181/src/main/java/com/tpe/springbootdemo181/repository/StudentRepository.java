package com.tpe.springbootdemo181.repository;

import com.tpe.springbootdemo181.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //Optional Since we have extended JpaRepository
public interface StudentRepository extends JpaRepository<Student,Long> {  //Pojo class, Primary Key data  Type
}
