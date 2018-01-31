package com.spring.start.service;

import com.spring.start.entity.Car;
import com.spring.start.repository.CarRepository;
import com.spring.start.service.dto.CarDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;

/**
 * Created by Nex0Zero on 2017-05-14.
 */
@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Spy
    private CarService carServiceSpy;
    @Mock
    private CarRepository carRepository;
    @Mock
    private Car car;
    @Mock
    private CarDto carDto;

    @Test
    public void shouldVerifyThat_CreateCar_IsCalled() throws Exception {
        //Arrange
        Mockito.doNothing().when(carServiceSpy).save(carDto);
        //Act
        carServiceSpy.save(carDto);
        //Assert
        Mockito.verify(carServiceSpy).save(carDto);
    }
    @Test
    public void shouldVerifyThat_RepositorySave_InCreateCar_IsCalled() throws Exception {
        //Arrange
        carServiceSpy.setCarRepository(carRepository);
        //Act
        carServiceSpy.save(carDto);
        //Assert
        Mockito.verify(carRepository).save(any(Car.class));
    }

    @Test
    public void shouldVerifyThat_FindAll_IsCalled() {
        //Arrange
        Mockito.doReturn(new ArrayList<Car>()).when(carServiceSpy).findAllActive();
        //Act
        carServiceSpy.findAllActive();
        //Assert
        Mockito.verify(carServiceSpy).findAllActive();
    }
    @Test
    public void shouldVerifyThat_RepositoryFfindCarsByDeletedFalse_InFindAll_IsCalled() throws Exception {
        //Arrange
        carServiceSpy.setCarRepository(carRepository);
        //Act
        carServiceSpy.findAllActive();
        //Assert
        Mockito.verify(carRepository).findCarsByDeletedFalse();
    }

    @Test
    public void shouldVerifyThat_DeleteCar_IsCalled() {
        //Arrange
        Mockito.doNothing().when(carServiceSpy).delete(5);
        //Act
        carServiceSpy.delete(5);
        //Assert
        Mockito.verify(carServiceSpy).delete(5);
    }
    @Test
    public void shouldVerifyThat_CarSetDeleted_InDeleteCar_IsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(car).when(carRepository).findOne((long)5);
        carServiceSpy.setCarRepository(carRepository);
        //Act
        carServiceSpy.delete(5);
        //Assert
        Mockito.verify(car).setDeleted(true);
    }

    @Test
    public void shouldVerifyThat_FindCarById_IsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(car).when(carServiceSpy).findById(5);
        //Act
        Car retrievedCar = carServiceSpy.findById(5);
        //Assert
        Mockito.verify(carServiceSpy).findById(5);
    }
    @Test
    public void shouldVerifyThat_RepositoryFindOne_InFindCarById_IsCalled() throws Exception {
        //Arrange
        carServiceSpy.setCarRepository(carRepository);
        //Act
        Car retrievedCar = carServiceSpy.findById(5);
        //Assert
        Mockito.verify(carRepository).findOne((long) 5);
    }

    @Test
    public void shouldVerifyThat_EditCar_IsCalled() throws Exception {
        //Arrange
        Mockito.doNothing().when(carServiceSpy).update(carDto);
        //Act
        carServiceSpy.update(carDto);
        //Assert
        Mockito.verify(carServiceSpy).update(carDto);
    }
//    @Test
//    public void shouldVerifyThat_RepositorySave_InEditCar_IsCalled() throws Exception {
//        //Arrange
//        carServiceSpy.setCarRepository(carRepository);
//        //Act
//        carServiceSpy.update(carDto);
//        //Assert
//        Mockito.verify(carRepository).save(any(Car.class));
//    }

    @Test
    public void shouldVerifyThat_FindCarsByIsTrailer_IsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(new ArrayList<Car>()).when(carServiceSpy).findCarsByIsTrailer();
        //Act
        carServiceSpy.findCarsByIsTrailer();
        //Assert
        Mockito.verify(carServiceSpy).findCarsByIsTrailer();
    }
    @Test
    public void shouldVerifyThat_RepositoryFindCarsByIsTrailerNotNull_InFindCarsByIsTrailer_IsCalled() throws Exception {
        //Arrange
        carServiceSpy.setCarRepository(carRepository);
        //Act
        carServiceSpy.findCarsByIsTrailer();
        //Assert
        Mockito.verify(carRepository).findCarsByIsTrailerTrueAndDeletedFalse();
    }

// -------------------------------------------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenFindCarByIdIsCalledWithoutContext() throws Exception {
        //Act
        Car retrievedCar = carServiceSpy.findById(5);
        //Assert
        assertThat(retrievedCar, is(equalTo(car)));
    }

    @Test
    public void example() throws Exception {
        //Arrange
        //Act
        //Assert
    }

}













