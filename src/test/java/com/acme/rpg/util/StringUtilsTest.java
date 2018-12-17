package com.acme.rpg.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the proper functionality of the StringUtils class
 * Created by Liodegar
 */
public class StringUtilsTest {


    @Test
    public void testGetLinesHappyPath() {
        //given
        String text = "A role-playing game (sometimes spelled roleplaying game[1][2] and abbreviated to RPG) " +
                "is a game in which players assume the roles of characters in a fictional setting.";

        //validate
        List<String> result = StringUtils.getLines(text, 50);

        List<String> expected = Arrays.asList("A role-playing game (sometimes spelled roleplaying",
                "game[1][2] and abbreviated to RPG) is a game in",
                "which players assume the roles of characters in a",
                "fictional setting.");

        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }

    @Test
    public void testGetLinesWithEmptyString() {
        //given
        String text = "";

        //validate
        List<String> result = StringUtils.getLines(text, 50);

        //then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLinesWithInvalidChunkLength() {
        //given
        String text = "A role-playing game (sometimes spelled roleplaying";

        //validate
        List<String> result = StringUtils.getLines(text, 0);
    }
}