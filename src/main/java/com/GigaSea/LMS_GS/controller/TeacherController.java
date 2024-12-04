package com.GigaSea.LMS_GS.controller;

import com.GigaSea.LMS_GS.model.Teacher;
import com.GigaSea.LMS_GS.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/addTeacher")
    public String addTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "addTeacher";
    }

    @PostMapping("/addTeacher")
    public String addTeacher(@ModelAttribute Teacher teacher, Model model) {
        Optional<Teacher> existingTeacher = teacherService.findTeacherById(teacher.getId());
        if (existingTeacher.isPresent()) {
            model.addAttribute("error", "Teacher with ID " + teacher.getId() + " already exists.");
            return "addTeacher";
        }
        teacherService.addTeacher(teacher);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/removeTeacher")
    public String removeTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "removeTeacher";
    }

    @PostMapping("/removeTeacher")
    public String removeTeacher(@RequestParam Long id, Model model) {
        Optional<Teacher> existingTeacher = teacherService.findTeacherById(id);
        if (!existingTeacher.isPresent()) {
            model.addAttribute("error", "Teacher with ID " + id + " does not exist.");
            return "removeTeacher";
        }
        teacherService.removeTeacher(id);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/updateTeacher")
    public String updateTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "updateTeacher";
    }

    @PostMapping("/updateTeacher")
    public String updateTeacher(@ModelAttribute Teacher teacher, Model model) {
        Optional<Teacher> existingTeacher = teacherService.findTeacherById(teacher.getId());
        if (!existingTeacher.isPresent()) {
            model.addAttribute("error", "Teacher with ID " + teacher.getId() + " does not exist.");
            return "updateTeacher";
        }
        teacherService.updateTeacher(teacher);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/viewTeachers")
    public String viewTeachers(Model model) {
        List<Teacher> teachers = teacherService.viewTeachers();
        model.addAttribute("teachers", teachers);
        return "viewTeachers";
    }

    @GetMapping("/teacherLogin")
    public String teacherLoginForm() {
        return "teacherLogin";
    }

    @PostMapping("/teacherLogin")
    public String teacherLogin(@RequestParam Long id, @RequestParam String name, Model model) {
        Optional<Teacher> existingTeacher = teacherService.findTeacherById(id);
        if (existingTeacher.isPresent() && existingTeacher.get().getName().equals(name)) {
            return "redirect:/teacherDashboard";
        } else {
            model.addAttribute("error", "Invalid ID or Name.");
            return "teacherLogin";
        }
    }

    @GetMapping("/adminLogin")
    public String adminLoginForm() {
        return "adminLogin";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if ("maheswar".equals(username) && "4613".equals(password)) {
            return "redirect:/adminDashboard";
        } else {
            model.addAttribute("error", "Invalid Username or Password.");
            return "adminLogin";
        }
    }
}
