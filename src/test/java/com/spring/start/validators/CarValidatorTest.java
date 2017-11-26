package com.spring.start.validators;

import com.spring.start.service.dto.CarDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.matches;

/**
 * Created by Nex0Zero on 2017-06-04.
 */
@RunWith(MockitoJUnitRunner.class)
public class CarValidatorTest {

    @Mock
    private Errors error;
    @Mock
    private Environment environment;

    CarDto car;


    @Test
    public void shouldValidateWithoutError() throws Exception {
        //Arrange
        car = new CarDto();
        car.setId(10);
        car.setBrand("brand");
        car.setModel("model");
        car.setName("name");
        car.setRegistrationNumber("EPA 1234");
        //Act

        //Assert
        assertEquals(0, error.getAllErrors().size());

    }

    @Test
    public void shouldValidateWithBrandError() throws Exception {
        //Arrange
        car = new CarDto();
        car.setId(10);
        car.setBrand("[{]}*()&^%$#@!");
        car.setModel("model");
        car.setName("name");
        car.setRegistrationNumber("EPA 1234");

        //Assert
        Mockito.verify(error).rejectValue( matches("brand"), any(String.class) );

    }

    @Test
    public void shouldValidateWithRegistrationNumberError() throws Exception {
        //Arrange
        car = new CarDto();
        car.setId(10);
        car.setBrand("brand");
        car.setModel("model");
        car.setName("name");
        car.setRegistrationNumber("EPA 1234-testRandomText*&^");

        //Assert
        Mockito.verify(error).rejectValue( matches("registrationNumber"), any(String.class) );

    }




}
























