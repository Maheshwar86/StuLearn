package com.GigaSea.LMS_GS.controller;

import com.GigaSea.LMS_GS.model.Student;
import com.GigaSea.LMS_GS.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // HTML File
    }

    @GetMapping("/teacherDashboard")
    public String teacherDashboard(Model model) {
        model.addAttribute("students", studentService.findStudents());
        return "teacherDashboard"; // HTML File
    }

    @GetMapping("/studentDashboard")
    public String studentDashboard(@RequestParam(value = "studentId", required = false) Long studentId, Model model) {
        if (studentId != null) {
            Student student = studentService.getStudentId(studentId);
            model.addAttribute("student", student);
        }
        return "studentDashboard"; // HTML File
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.findStudents());
        return "students"; // HTML File
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/teacherDashboard";
    }

    @GetMapping("/registerStudent")
    public String registerStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "registerStudent";
    }

    @GetMapping("/updateStudent/{id}")
    public String updateStudent(Model model, @PathVariable Long id) {
        Student student = studentService.getStudentId(id);
        model.addAttribute("student", student);
        return "updateFormStudent";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/teacherDashboard";
    }

    @GetMapping("/viewStudent/{id}")
    public String viewStudent(Model model, @PathVariable Long id) {
        Student student = studentService.getStudentId(id);
        model.addAttribute("student", student);
        return "viewStudent";
    }

    @PostMapping("/updateAttendance/{id}")
    public String updateAttendance(@PathVariable Long id, @RequestParam("attendance") String attendance) {
        Student student = studentService.getStudentId(id);
        student.setAttendance(attendance);
        studentService.saveStudent(student);
        return "redirect:/attendance";
    }

    @GetMapping("/attendance")
    public String attendance(@RequestParam(value = "date", required = false) LocalDate date, Model model) {
        List<Student> students;
        if (date != null) {
            students = studentService.findStudentsByDate(date);
        } else {
            students = studentService.findStudents();
        }
        model.addAttribute("students", students);
        return "attendance";
    }
}
