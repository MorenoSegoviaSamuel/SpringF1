package org.api.springf1.service;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.model.Driver;
import org.api.springf1.repository.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DriverServiceImplTest {

    @Mock
    DriverRepository driverRepository;
    @InjectMocks
    DriverServiceImpl driverService;
    Driver driver;
    DriverDTO driverDTO;

    @BeforeEach
    public void setup(){
        driver = Driver.builder().id(1L).code("AAA").forename("Aytron").surname("Senna").build();
        driverDTO = DriverDTO.builder().id(1L).code("AAA").forename("Aytron").surname("Senna").build();
    }

    @Test
    public void shoulReturnDriverDTOWhenCreateDriver(){
        //Given
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);
        //When
        DriverDTO saveDriver = driverService.saveDriver(driver);
        //Then
        assertNotNull(saveDriver);
        assertEquals("AAA", saveDriver.code());

        verify(driverRepository, times(1)).save(driver);

    }

    public void shouldReturnDriverDTOWhenFindDriverByCode(){
        when(driverRepository.findByCodeIgnoreCase("AAA")).thenReturn(Optional.of(driver));

        DriverDTO getDriverByCode = driverService.getDriverByCode("AAA");

        assertNotNull(getDriverByCode);
        assertEquals("AAA", getDriverByCode.code());
    }
}