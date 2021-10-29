package com.ileiwe.data.repository;

import com.ileiwe.data.model.Authority;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.data.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

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
}