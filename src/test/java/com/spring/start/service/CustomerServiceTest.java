package com.spring.start.service;

import com.spring.start.entity.Customer;
import com.spring.start.repository.CustomerRepository;
import com.spring.start.service.dto.CustomerDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Matchers.any;

/**
 * Created by Nex0Zero on 2017-05-15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Spy
    private CustomerService customerServiceSpy;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private Customer customer;
    @Mock
    private CustomerDto customerDto;

    @Test
    public void shouldVerifyThat_CreateCustomer_IsCalled() throws Exception {
        //Arrange
        Mockito.doNothing().when(customerServiceSpy).save(customerDto);
        //Act
        customerServiceSpy.save(customerDto);
        //Assert
        Mockito.verify(customerServiceSpy).save(customerDto);
    }
    @Test
    public void shouldVerifyThat_RepositorySave_InCreateCustomer_IsCalled() throws Exception {
        //Arrange
        customerServiceSpy.setCustomerRepository(customerRepository);
        //Act
        customerServiceSpy.save(customerDto);
        //Assert
        Mockito.verify(customerRepository).save(any(Customer.class));
    }

//    @Test
//    public void shouldVerifyThat_EditCustomer_IsCalled() throws Exception {
//        //Arrange
//        Mockito.doNothing().when(customerServiceSpy).update(customerDto);
//        //Act
//        customerServiceSpy.update(customerDto);
//        //Assert
//        Mockito.verify(customerServiceSpy).update(customerDto);
//    }
    @Test
    public void shouldVerifyThat_RepositorySave_InEditCustomer_IsCalled() throws Exception {
        //Arrange
        customerServiceSpy.setCustomerRepository(customerRepository);
        //Act
        customerServiceSpy.save(customerDto);
        //Assert
        Mockito.verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void shouldVerifyThat_FindAll_IsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(new ArrayList<Customer>()).when(customerServiceSpy).findAllActive();
        //Act
        customerServiceSpy.findAllActive();
        //Assert
        Mockito.verify(customerServiceSpy).findAllActive();
    }
    @Test
    public void shouldVerifyThat_RepositoryFindAll_FindAll_IsCalled() throws Exception {
        //Arrange
        customerServiceSpy.setCustomerRepository(customerRepository);
        //Act
        customerServiceSpy.findAllActive();
        //Assert
        Mockito.verify(customerRepository).findAll();
    }

    @Test
    public void shouldVerifyThat_DeleteCustomer_IsCalled() throws Exception {
        //Arrange
        Mockito.doNothing().when(customerServiceSpy).delete(5);
        //Act
        customerServiceSpy.delete(5);
        //Assert
        Mockito.verify(customerServiceSpy).delete(5);
    }
    @Test
    public void shouldVerifyThat_RepositoryDelete_InDeleteCustomer_IsCalled() throws Exception {
        //Arrange
        customerServiceSpy.setCustomerRepository(customerRepository);
        //Act
        customerServiceSpy.delete(5);
        //Assert
        Mockito.verify(customerRepository).delete((long) 5);
    }

    @Test
    public void shouldVerifyThat_FindCustomerById_IsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(customer).when(customerServiceSpy).findById(5);
        //Act
        customerServiceSpy.findById(5);
        //Assert
        Mockito.verify(customerServiceSpy).findById(5);
    }
    @Test
    public void shouldVerifyThat_RepositoryFindOne_InFindCustomerById_IsCalled() throws Exception {
        //Arrange
        customerServiceSpy.setCustomerRepository(customerRepository);
        //Act
        customerServiceSpy.findById(5);
        //Assert
        Mockito.verify(customerRepository).findOne((long) 5);
    }


}
























