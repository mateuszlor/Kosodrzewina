package com.spring.start.service;

import com.spring.start.entity.Dictionary;
import com.spring.start.entity.DictionaryType;
import com.spring.start.repository.DictionaryRepository;
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
public class DictionaryServiceTest {

    @Spy
    private DictionaryService dictionaryServiceSpy;
    @Mock
    private DictionaryRepository dictionaryRepository;
    @Mock
    private Dictionary dictionary;

    @Test
    public void shouldVerifyThat_AddEntry_IsCalled() throws Exception {
        //Arrange
        Mockito.doNothing().when(dictionaryServiceSpy).addEntry("name", DictionaryType.PAYMENT );
        //Act
        dictionaryServiceSpy.addEntry("name", DictionaryType.PAYMENT );
        //Assert
        Mockito.verify(dictionaryServiceSpy).addEntry("name", DictionaryType.PAYMENT );
    }
    @Test
    public void shouldVerifyThat_RepositorySave_InAddEntry_IsCalled() throws Exception {
        //Arrange
        dictionaryServiceSpy.setDictionaryRepository(dictionaryRepository);
        //Act
        dictionaryServiceSpy.addEntry("name", DictionaryType.PAYMENT );
        //Assert
        Mockito.verify(dictionaryRepository).save(any(Dictionary.class));
    }

    @Test
    public void shouldVerifyThat_FindAll_IsCalled() {
        //Arrange
        Mockito.doReturn(new ArrayList<Dictionary>()).when(dictionaryServiceSpy).findAll();
        //Act
        dictionaryServiceSpy.findAll();
        //Assert
        Mockito.verify(dictionaryServiceSpy).findAll();
    }
    @Test
    public void shouldVerifyThat_RepositoryFindAll_InFindAll_IsCalled() throws Exception {
        //Arrange
        dictionaryServiceSpy.setDictionaryRepository(dictionaryRepository);
        //Act
        dictionaryServiceSpy.findAll();
        //Assert
        Mockito.verify(dictionaryRepository).findAll();
    }

    @Test
    public void shouldVerifyThat_GetDictionaryiesByType_IsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(new ArrayList<Dictionary>()).when(dictionaryServiceSpy).getDictionaryiesByType(DictionaryType.PAYMENT);
        //Act
        dictionaryServiceSpy.getDictionaryiesByType(DictionaryType.PAYMENT);
        //Assert
        Mockito.verify(dictionaryServiceSpy).getDictionaryiesByType(DictionaryType.PAYMENT);
    }
    @Test
    public void shouldVerifyThat_RepositoryGetDictionariesByType_InGetDictionaryiesByType_IsCalled() throws Exception {
        //Arrange
        dictionaryServiceSpy.setDictionaryRepository(dictionaryRepository);
        //Act
        dictionaryServiceSpy.getDictionaryiesByType(DictionaryType.PAYMENT);
        //Assert
        Mockito.verify(dictionaryRepository).getDictionariesByType(DictionaryType.PAYMENT);
    }

    @Test
    public void shouldVerifyThat_DeleteEntry_IsCalled() {
        //Arrange
        Mockito.doNothing().when(dictionaryServiceSpy).deleteEntry(5);
        //Act
        dictionaryServiceSpy.deleteEntry(5);
        //Assert
        Mockito.verify(dictionaryServiceSpy).deleteEntry(5);
    }
    @Test
    public void shouldVerifyThat_RepositoryDelete_InDeleteEntry_IsCalled() throws Exception {
        //Arrange
        dictionaryServiceSpy.setDictionaryRepository(dictionaryRepository);
        //Act
        dictionaryServiceSpy.deleteEntry(5);
        //Assert
        Mockito.verify(dictionaryRepository).delete((long) 5);
    }

    @Test
    public void shouldVerifyThat_FindEntryById_IsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(dictionary).when(dictionaryServiceSpy).findEntryById(5);
        //Act
        dictionaryServiceSpy.findEntryById(5);
        //Assert
        Mockito.verify(dictionaryServiceSpy).findEntryById(5);
    }
    @Test
    public void shouldVerifyThat_RepositoryFindOne_InFindEntryById_IsCalled() throws Exception {
        //Arrange
        dictionaryServiceSpy.setDictionaryRepository(dictionaryRepository);
        //Act
        dictionaryServiceSpy.findEntryById(5);
        //Assert
        Mockito.verify(dictionaryRepository).findOne((long) 5);
    }

    @Test
    public void shouldVerifyThat_EditEntry_IsCalled() throws Exception {
        //Arrange
        Mockito.doNothing().when(dictionaryServiceSpy).editEntry(5, "name", DictionaryType.PAYMENT);
        //Act
        dictionaryServiceSpy.editEntry(5, "name", DictionaryType.PAYMENT);
        //Assert
        Mockito.verify(dictionaryServiceSpy).editEntry(5, "name", DictionaryType.PAYMENT);
    }
    @Test
    public void shouldVerifyThat_RepositorySave_InEditEntry_IsCalled() throws Exception {
        //Arrange
        dictionaryServiceSpy.setDictionaryRepository(dictionaryRepository);
        //Act
        dictionaryServiceSpy.editEntry(5, "name", DictionaryType.PAYMENT);
        //Assert
        Mockito.verify(dictionaryRepository).save(any(Dictionary.class));
    }


}
