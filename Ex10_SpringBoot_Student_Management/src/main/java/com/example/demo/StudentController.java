package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Display student form
    @GetMapping("/")
    public String showForm() {
        return "student-form";
    }

    // Read form and save student
    @PostMapping("/saveStudent")
    public String saveStudent(Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // Display all students
    @GetMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student-list";
    }

    // Display edit form for one student
    @GetMapping("/editStudent/{regno}")
    public String editStudent(@PathVariable int regno, Model model) {
        Student student = studentService.getStudentById(regno);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "student-edit";
    }

    // Read edit form and update student
    @PostMapping("/updateStudent")
    public String updateStudent(Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // Delete student
    @PostMapping("/deleteStudent/{regno}")
    public String deleteStudent(@PathVariable int regno) {
        studentService.deleteStudent(regno);
        return "redirect:/students";
    }
}