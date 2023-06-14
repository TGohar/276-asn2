package com.example.students.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.students.models.Student;
import com.example.students.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/students/view")
    public String getAllStudents(Model model) {

        System.out.println("Getting all students");

        List<Student> students = studentRepo.findAll();

        model.addAttribute("st", students);

        return "students/showAll";
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response) {

        System.out.println("ADD student");

        String newName = newStudent.get("name");
        int newWeight = Integer.parseInt(newStudent.get("weight"));
        int newHeight = Integer.parseInt(newStudent.get("height"));
        String newHairColour = newStudent.get("hairColour");
        int newGPA = Integer.parseInt(newStudent.get("gpa"));
        String newMajor = newStudent.get("major");
        int newYear = Integer.parseInt(newStudent.get("year"));
        studentRepo.save(new Student(newName, newWeight, newHeight, newHairColour, newGPA, newMajor, newYear));

        response.setStatus(201);

        return "redirect:/students/view";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {

        System.out.println("DELETE student");

        studentRepo.deleteById(id);

        return "redirect:/students/view";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable int id, Model model) {

        System.out.println("EDIT student");

        model.addAttribute("student", studentRepo.findById(id));

        return "students/editStudent";
    }

    @PostMapping("students/{id}")
    public String updateStudent(@PathVariable int id, @ModelAttribute("student") Student student, Model model) {

        System.out.println("UPDATE student");

        Student studentBeingEdited = (Student) studentRepo.findById(id);
        
        studentBeingEdited.setId(id);
        studentBeingEdited.setName(student.getName());
        studentBeingEdited.setWeight(student.getWeight());
        studentBeingEdited.setHeight(student.getHeight());
        studentBeingEdited.setHairColour(student.getHairColour());
        studentBeingEdited.setGpa(student.getGpa());
        studentBeingEdited.setMajor(student.getMajor());
        studentBeingEdited.setYear(student.getYear());

        studentRepo.save(studentBeingEdited);

        return "redirect:/students/view";
    }

}
