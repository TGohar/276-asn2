package com.example.students.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    List<Student> findById(int id);
    List<Student> findByName(String name);
    List<Student> findByWeight(int weight);
    List<Student> findByHeight(int height);
    List<Student> findByHairColour(String hairColour);
    List<Student> findByGpa(int gpa);
    List<Student> findByMajor(String major);
    List<Student> findByYear(int year);
}
