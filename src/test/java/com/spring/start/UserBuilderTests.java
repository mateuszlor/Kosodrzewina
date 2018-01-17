package com.spring.start;

import com.spring.start.entity.Role;
import com.spring.start.entity.User;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Mateusz on 20.03.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@var
@Log4j
public class UserBuilderTests {
    @Test
    public void shouldBuildEmptyUser() {
        //given
        //when
        var generated = User.builder().build();
        //then
        Assert.assertNull(generated.getName());
        Assert.assertNull(generated.getSurname());
        Assert.assertNull(generated.getUsername());
        Assert.assertNull(generated.getPassword());
    }

    @Test
    public void shouldBuildName() {
        //given
        var name = "XYZ";
        //when
        var generated = User.builder().name(name).build();
        //then
        Assert.assertEquals(name, generated.getName());
    }

    @Test
    public void shouldBuildSurname() {
        //given
        var surname = "XYZ";
        //when
        var generated = User.builder().surname(surname).build();
        //then
        Assert.assertEquals(surname, generated.getSurname());
    }

    @Test
    public void shouldBuildUsername() {
        //given
        var username = "XYZ";
        //when
        var generated = User.builder().username(username).build();
        //then
        Assert.assertEquals(username, generated.getUsername());
    }
}
