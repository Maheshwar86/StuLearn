package com.GigaSea.LMS_GS.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {

    @GetMapping("/java_quiz")
    public String javaQuiz() {
        return "java_quiz";
    }

    @GetMapping("/html_quiz")
    public String htmlQuiz() {
        return "html_quiz";
    }

    @GetMapping("/cpp_quiz")
    public String cppQuiz() {
        return "cpp_quiz";
    }

    @GetMapping("/python_quiz")
    public String pythonQuiz() {
        return "python_quiz";
    }

    @GetMapping("/javascript_quiz")
    public String javascriptQuiz() {
        return "javascript_quiz";
    }

    @GetMapping("/fullstack_quiz")
    public String fullstackQuiz() {
        return "fullstack_quiz";
    }

    @GetMapping("/c_quiz")
    public String cQuiz() {
        return "c_quiz";
    }
}
