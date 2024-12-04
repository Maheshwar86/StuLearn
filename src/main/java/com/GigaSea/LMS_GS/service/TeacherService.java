package com.GigaSea.LMS_GS.service;

import com.GigaSea.LMS_GS.model.Teacher;
import com.GigaSea.LMS_GS.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void removeTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public void updateTeacher(Teacher updatedTeacher) {
        teacherRepository.save(updatedTeacher);
    }

    public List<Teacher> viewTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findTeacherById(Long id) {
        return teacherRepository.findById(id);
    }
}
