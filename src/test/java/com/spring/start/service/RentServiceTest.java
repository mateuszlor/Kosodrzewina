package com.spring.start.service;

import com.spring.start.entity.Rent;
import com.spring.start.operations.Functions;
import com.spring.start.repository.CarRepository;
import com.spring.start.repository.CustomerRepository;
import com.spring.start.repository.RentRepository;
import com.spring.start.repository.UserRepository;
import com.spring.start.service.dto.RentDto;
import com.spring.start.service.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Matchers.any;

/**
 * Created by Nex0Zero on 2017-05-15.
 */
@RunWith(MockitoJUnitRunner.class)
public class RentServiceTest {

    @Spy
    private RentService rentServiceSpy;
    @Mock
    private RentRepository rentRepository;
    @Mock
    private Rent rent;
    @Mock
    private RentDto rentDto;
    @Mock
    private UserDto userDto;

    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Mock
    private CarService carService;
    @Mock
    private CarRepository carRepository;

    @Mock
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void before() {
        rentServiceSpy.setRentRepository(rentRepository);

        userService.setUserRepository(userRepository);
        rentServiceSpy.setUserService(userService);

        carService.setCarRepository(carRepository);
        rentServiceSpy.setCarService(carService);

        customerService.setCustomerRepository(customerRepository);
        rentServiceSpy.setCustomerService(customerService);
    }

    @Test
    public void shouldVerifyThat_AddRent_IsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(rent).when(rentServiceSpy).addRent(rentDto, userDto, rent);
        //Act
        rentServiceSpy.addRent(rentDto, userDto, rent);
        //Assert
        Mockito.verify(rentServiceSpy).addRent(rentDto, userDto, rent);
    }

    @Test
    public void shouldVerifyThat_FindAll_IsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(new ArrayList<Rent>()).when(rentServiceSpy).findAll();
        //Act
        rentServiceSpy.findAll();
        //Assert
        Mockito.verify(rentServiceSpy).findAll();
    }
    @Test
    public void shouldVerifyThat_RepositoryFindAll_FindAll_IsCalled() throws Exception {
        //Act
        rentServiceSpy.findAll();
        //Assert
        Mockito.verify(rentRepository).findAll();
    }

    @Test
    public void shouldVerifyThat_FindById_IsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(rent).when(rentServiceSpy).findById(5);
        //Act
        rentServiceSpy.findById(5);
        //Assert
        Mockito.verify(rentServiceSpy).findById(5);
    }
    @Test
    public void shouldVerifyThat_RepositoryFindOne_InFindById_IsCalled() throws Exception {
        //Act
        rentServiceSpy.findById(5);
        //Assert
        Mockito.verify(rentRepository).findOne((long) 5);
    }

    @Test
    public void shouldVerifyThat_Delete_IsCalled() throws Exception {
        //Arrange
        Mockito.doNothing().when(rentServiceSpy).delete(5);
        //Act
        rentServiceSpy.delete(5);
        //Assert
        Mockito.verify(rentServiceSpy).delete(5);
    }
    @Test
    public void shouldVerifyThat_RepositoryDelete_InDelete_IsCalled() throws Exception {
        //Act
        rentServiceSpy.delete(5);
        //Assert
        Mockito.verify(rentRepository).delete((long) 5);
    }

    @Test
    public void shouldVerifyThat_ReturnRent_IsCalled() throws Exception {
        //Arrange
        Mockito.doNothing().when(rentServiceSpy).returnRent(5, "01-01-2000", (long) 9);
        //Act
        rentServiceSpy.returnRent(5, "01-01-2000", (long) 9);
        //Assert
        Mockito.verify(rentServiceSpy).returnRent(5, "01-01-2000", (long) 9);
    }
    @Test
    public void shouldVerifyThat_RepositorySave_InReturnRent_IsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(rent).when(rentRepository).findOne(any());
        Mockito.doNothing().when(rent).setEndDate(any());
        //Act
        rentServiceSpy.returnRent(5, "01-01-2000", (long) 9);
        //Assert
        Mockito.verify(rentRepository).save(any(Rent.class));
    }

    @Test
    public void shouldVerifyThat_UpdateRent_IsCalled() throws Exception {
        //Arrange
        Mockito.doNothing().when(rentServiceSpy).update(rent);
        //Act
        rentServiceSpy.update(rent);
        //Assert
        Mockito.verify(rentServiceSpy).update(rent);
    }
    @Test
    public void shouldVerifyThat_RepositorySave_InUpdateRent_IsCalled() throws Exception {
        //Act
        rentServiceSpy.update(rent);
        //Assert
        Mockito.verify(rentRepository).save(rent);
    }

}






















