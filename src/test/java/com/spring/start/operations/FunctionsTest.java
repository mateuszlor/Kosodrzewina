package com.spring.start.operations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nex0Zero on 2017-06-06.
 */
@RunWith(Parameterized.class)
public class FunctionsTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Raport type from-to.pdf", "", "from", "to", "type"}, //angielski
                {"Raport typ z-do.pdf", "", "z", "do", "typ"}, //polski
                {"Raport typ von-nach.pdf", "", "von", "nach", "typ"}, //niemiecki
                {"Raport matang gikan sa-sa.pdf", "", "gikan sa", "sa", "matang"}, //cebuanski
                {"Raport tyyppi alkaen-etta.pdf", "", "alkaen", "etta", "tyyppi"}, //finski
                {"Raport wypozyczenia 2017-06-06-2017-06-16.pdf", "", "2017-06-06", "2017-06-16", "wypozyczenia"},
                {"Raport.pdf", "", "", "", ""},
                {"nazwa.pdf", "nazwa", "", "", ""},
                {"nazwa.pdf", "nazwa.pdf", "", "", ""}
        });
    }

    private String expected;
    private String filename;
    private String from;
    private String to;
    private String type;

    public FunctionsTest(String expected, String filename,
                         String from, String to, String type) {

        this.expected = expected;
        this.filename = filename;
        this.from = from;
        this.to = to;
        this.type = type;
    }

    @Test
    public void parametricTest() {
        //Assert
        assertEquals(expected, Functions.generateFilename(filename, from, to, type));
    }



}




















