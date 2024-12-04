package com.GigaSea.LMS_GS.controller;

import com.GigaSea.LMS_GS.model.MessageRequest;
import com.GigaSea.LMS_GS.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/validateStudent")
    public ResponseEntity<?> validateStudent(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        System.out.println("Received email: " + email);
        boolean isValid = studentService.isStudentEmailValid(email);
        if (isValid) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequest messageRequest) {
        studentService.sendMessageToParent(messageRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getMessages/{email}")
    public ResponseEntity<List<String>> getMessages(@PathVariable String email) {
        List<String> messages = studentService.getMessagesForStudent(email);
        return ResponseEntity.ok(messages);
    }
}
