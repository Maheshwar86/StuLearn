package com.GigaSea.LMS_GS.repository;

import com.GigaSea.LMS_GS.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmailAndName(String email, String name);

    Optional<Student> findByEmail(String email);
}
