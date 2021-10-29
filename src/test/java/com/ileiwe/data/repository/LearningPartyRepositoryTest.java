package com.ileiwe.data.repository;

import com.ileiwe.data.model.Authority;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.data.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
public class LearningPartyRepositoryTest {

    @Autowired
    LearningPartyRepository learningPartyRepository;

    @BeforeEach
    void setUp(){
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void createdLearningPartyWithStudentRoleTest(){
        LearningParty learningUser =
                new LearningParty("you@email.com", "you123", new Authority(Role.ROLE_STUDENT));

        log.info("Before saving -> {}", learningUser);
        learningPartyRepository.save(learningUser);
        assertThat(learningUser.getId()).isNotNull();
        assertThat(learningUser.getEmail()).isEqualTo("you@email.com");
        assertThat(learningUser.getAuthorities().get(0).getAuthority()).isEqualTo(Role.ROLE_STUDENT);
        assertThat(learningUser.getAuthorities().get(0).getId()).isNotNull();
        log.info("After saving -> {}", learningUser);
    }

    @Test
    void createdLearningPartyUniqueEmailsTest(){
        LearningParty learningUser1 =
                new LearningParty("you@email.com", "you123", new Authority(Role.ROLE_STUDENT));
        learningPartyRepository.save(learningUser1);
        assertThat(learningUser1.getId()).isNotNull();
        assertThat(learningUser1.getEmail()).isEqualTo("you@email.com");

        LearningParty learningUser2 =
                new LearningParty("you@email.com", "you123", new Authority(Role.ROLE_STUDENT));
        assertThrows(DataIntegrityViolationException.class, () -> learningPartyRepository.save(learningUser2));
    }

    @Test
    @Transactional
    void createLearningPartyWithNullValuesTest(){
        //create a learning party with null values
        LearningParty learningUser =
                new LearningParty(null, null, new Authority(Role.ROLE_STUDENT));
        assertThrows(ConstraintViolationException.class, () -> learningPartyRepository.save(learningUser));
    }

    @Test
    void createLearningPartyWithEmptyStringValuesTest(){
        //create a learning party with empty string values
        LearningParty learningUser =
                new LearningParty("", "", new Authority(Role.ROLE_STUDENT));
        assertThrows(ConstraintViolationException.class, () ->
                learningPartyRepository.save(learningUser));
    }

    @Test
    void createLearningPartyWithWhiteSpaceTest(){
        LearningParty learningUser =
                new LearningParty(" ", " ", new Authority(Role.ROLE_STUDENT));
        assertThrows(ConstraintViolationException.class, () ->
                learningPartyRepository.save(learningUser));
    }

    @Test
    @Transactional
    void findByUserNameTest(){
        LearningParty user = learningPartyRepository.findByEmail("you@email.com");
        learningPartyRepository.save(user);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getEmail()).isEqualTo("you@email.com");

        log.info("learning party object -> {}", user);

    }

    @AfterEach
    void tearDown(){
    }
}
