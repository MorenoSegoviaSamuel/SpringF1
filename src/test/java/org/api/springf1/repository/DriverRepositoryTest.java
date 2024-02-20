package org.api.springf1.repository;

import org.api.springf1.model.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DriverRepositoryTest {

    @Autowired
    DriverRepository driverRepository;

    Driver driver;

    @BeforeEach
    public void setup() {
        driver = Driver.builder().id(1L).code("AAA").forename("Aytron").surname("Senna").build();
    }

    @Test
    void shouldReturnSavedDriverWhen(){
        //Given
        //When
        Driver driverSaved = driverRepository.save(driver);

        //Then
        assertThat(driverSaved).isNotNull();
        assertThat(driverSaved.getId()).isGreaterThan(0);
    }

    @Test
    void shouldReturnMoreThanOneDriverWhenSaveTwoDrivers(){
        driverRepository.save(driver);
        driverRepository.save(Driver.builder().id(2L).code("AAB").forename("Aytron").surname("Senna").build());


        List<Driver> drivers = driverRepository.findAll();

        assertThat(drivers).isNotNull();
        assertEquals(drivers.size(),2);
    }

    @Test
    void shouldReturnDriverNotNullWhenFindByCode(){

        driverRepository.save(driver);
        Assertions.assertNotNull(driverRepository.findByCodeIgnoreCase("AAA").get());
    }

    @Test
    void shouldReturnDriverNotNullWhenUpdateDriver(){

        driverRepository.save(driver);
        driverRepository.save(driver);

        assertThat(driver).isNotNull();
    }

    @Test
    void shouldReturnNullDriverWhenDelete(){

        driverRepository.save(driver);

        driverRepository.deleteByCodeIgnoreCase("AAA");

        Driver driver = driverRepository.findByCodeIgnoreCase("AAA").orElse(null);
        assertThat(driver).isNull();
    }


}