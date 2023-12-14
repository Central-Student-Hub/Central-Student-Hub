package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentProfileRepositoryTest {
    @Autowired
    private StudentProfileRepository studentProfileRepository;
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        StudentProfile student = StudentProfile.builder()
                .studentId(1)
                .firstName("Ziad")
                .lastName("Reda")
                .biography("hello")
                .profilePictureUrl("ziad.png")
                .major("a")
                .minor("b")
                .level(1)
                .noOfHours(115)
                .gpa(3.99)
                .contacts(null)
                .warnings(null)
                .grades(null)
                .assignmentAnswers(null)
                .registrations(null)
                .build();
        entityManager.persist(student);
    }

    @Test
    void existingStudentFindById() {
        Optional<StudentProfile> student = studentProfileRepository.findById(1);
        assertTrue(student.isPresent());
    }
}