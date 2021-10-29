package com.ileiwe.data.repository;

import com.ileiwe.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
class InstructorRepositoryTest {

    @Autowired
    InstructorRepository instructorRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveInstructorAsLearningParty(){
        //create a learning party
        LearningParty user =
                new LearningParty("trainer@email.com", "trainer123", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        Instructor instructor = Instructor.builder().firstname("Nico").lastname("Mbaga").learningParty(user).build();

        log.info("Instructor before saving -> {}", instructor);
        instructorRepository.save(instructor);
        //save instructor

        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving -> {}", instructor);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updateInstructorTableAfterCreate(){
        LearningParty user =
                new LearningParty("trainer@email.com", "trainer123", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        Instructor instructor = Instructor.builder().firstname("Nico").lastname("Mbaga").learningParty(user).build();

        log.info("Instructor before saving -> {}", instructor);
        instructorRepository.save(instructor);

        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving -> {}", instructor);

        Instructor savedInstructor = instructorRepository.findById(instructor.getId()).orElse(null);
        log.info("saved instructor -> {}", instructor);

        //assert that saved instructor is not null
        assertThat(savedInstructor).isNotNull();
        //confirm that instructor's bio and gender is null
        assertThat(savedInstructor.getBio()).isNull();
        assertThat(savedInstructor.getGender()).isNull();

        //update and save bio and gender
        savedInstructor.setBio("I am a java instructor");
        savedInstructor.setGender(Gender.FEMALE);
        instructorRepository.save(savedInstructor);

        //assert that instructor now has bio and gender
        assertThat(savedInstructor.getBio()).isNotNull();
        assertThat(savedInstructor.getGender()).isNotNull();
    }

    @Test
    void createInstructorWithNullValuesTest(){
        LearningParty user =
                new LearningParty("trainer@email.com", "trainer123", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor and map with learning party
        Instructor instructor = Instructor.builder().firstname(null).lastname(null).learningParty(user).build();

        assertThrows(ConstraintViolationException.class, ()-> instructorRepository.save(instructor));
    }

    @Test
    void createInstructorWithEmptyStringValuesTest(){
        LearningParty user =
                new LearningParty("trainer@email.com", "trainer123", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor and map with learning party
        Instructor instructor = Instructor.builder().firstname("").lastname("").learningParty(user).build();

        assertThrows(ConstraintViolationException.class, ()-> instructorRepository.save(instructor));
    }

    @Test
    void createInstructorWithWhiteSpaceTest(){
        LearningParty user =
                new LearningParty("trainer@email.com", "trainer123", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor and map with learning party
        Instructor instructor = Instructor.builder().firstname(" ").lastname(" ").learningParty(user).build();

        assertThrows(ConstraintViolationException.class, ()-> instructorRepository.save(instructor));
    }
}