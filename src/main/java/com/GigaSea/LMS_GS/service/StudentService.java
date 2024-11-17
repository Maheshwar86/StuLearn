package com.GigaSea.LMS_GS.service;

import com.GigaSea.LMS_GS.model.Student;
import com.GigaSea.LMS_GS.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentId(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentsByDate(LocalDate date) {
        // Implement logic to find students by date
        // This is a placeholder implementation
        return studentRepository.findAll();
    }
}
