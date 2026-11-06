package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dao.StudentDAO;
import com.example.model.Student;

/**
 * Step 8 and 9: controller that receives client requests and calls the DAO.
 */
@Controller
public class StudentController {

    @Autowired
    private StudentDAO studentDAO;

    // Home page -> list of all students
    @GetMapping("/")
    public String home() {
        return "redirect:/students";
    }

    // 2. Display all students
    @GetMapping("/students")
    public String list(Model model) {
        model.addAttribute("students", studentDAO.findAll());
        return "students";
    }

    // 1. Add a new student - show the empty form
    @GetMapping("/students/new")
    public String newForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("editMode", false);
        return "student-form";
    }

    // 1. Add a new student - save
    @PostMapping("/students/save")
    public String save(@ModelAttribute("student") Student student,
                       Model model, RedirectAttributes ra) {
        if (studentDAO.findById(student.getRegno()) != null) {
            model.addAttribute("editMode", false);
            model.addAttribute("error", "Register number " + student.getRegno() + " already exists.");
            return "student-form";
        }
        studentDAO.save(student);
        ra.addFlashAttribute("message", "Student added successfully.");
        return "redirect:/students";
    }

    // 3. Search a student by ID
    @GetMapping("/students/search")
    public String search(@RequestParam(value = "regno", required = false) Integer regno, Model model) {
        if (regno != null) {
            Student found = studentDAO.findById(regno);
            model.addAttribute("searched", true);
            model.addAttribute("regno", regno);
            model.addAttribute("student", found);
        }
        return "search";
    }

    // 4. Update student details - show the form filled with current values
    @GetMapping("/students/edit/{regno}")
    public String editForm(@PathVariable("regno") int regno, Model model, RedirectAttributes ra) {
        Student s = studentDAO.findById(regno);
        if (s == null) {
            ra.addFlashAttribute("error", "No student found with register number " + regno + ".");
            return "redirect:/students";
        }
        model.addAttribute("student", s);
        model.addAttribute("editMode", true);
        return "student-form";
    }

    // 4. Update student details - save
    @PostMapping("/students/update")
    public String update(@ModelAttribute("student") Student student, RedirectAttributes ra) {
        studentDAO.update(student);
        ra.addFlashAttribute("message", "Student " + student.getRegno() + " updated successfully.");
        return "redirect:/students";
    }

    // 5. Delete a student by ID (POST so a link click or crawler can't delete by accident)
    @PostMapping("/students/delete")
    public String delete(@RequestParam("regno") int regno, RedirectAttributes ra) {
        if (studentDAO.deleteById(regno)) {
            ra.addFlashAttribute("message", "Student " + regno + " deleted successfully.");
        } else {
            ra.addFlashAttribute("error", "No student found with register number " + regno + ".");
        }
        return "redirect:/students";
    }
}
