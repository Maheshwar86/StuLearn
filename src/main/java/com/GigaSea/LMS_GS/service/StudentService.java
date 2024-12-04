package com.GigaSea.LMS_GS.service;

import com.GigaSea.LMS_GS.model.MessageRequest;
import com.GigaSea.LMS_GS.model.Student;
import com.GigaSea.LMS_GS.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentId(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

    public Student getStudentByEmailAndName(String email, String name) {
        return studentRepository.findByEmailAndName(email, name)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

    @Transactional
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setGender(updatedStudent.getGender());
        existingStudent.setCourse(updatedStudent.getCourse());
        existingStudent.setCountry(updatedStudent.getCountry());
        existingStudent.setState(updatedStudent.getState());
        existingStudent.setVillage(updatedStudent.getVillage());
        existingStudent.setBloodGroup(updatedStudent.getBloodGroup());
        existingStudent.setDescription(updatedStudent.getDescription());
        existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
        existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());

        return studentRepository.save(existingStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public boolean isStudentEmailValid(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

    public List<String> getMessagesForStudent(String email) {
        // Implement the logic to fetch messages for a student based on their email
        // This is a placeholder implementation
        // Replace this with actual logic to retrieve messages from a database or other source
        return List.of("Message 1 for " + email, "Message 2 for " + email);
    }

    public void sendMessageToParent(MessageRequest messageRequest) {
        // Implement the logic to send a message to a parent
        // This is a placeholder implementation
        // Replace this with actual logic to send messages
        System.out.println("Sending message to parent: " + messageRequest.getMessage());
    }
}
