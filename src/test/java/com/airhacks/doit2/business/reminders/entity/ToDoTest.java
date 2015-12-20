package com.airhacks.doit2.business.reminders.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test encji ToDo
 */
public class ToDoTest {

    @Test
    public void validToDo()
    {
        ToDo valid = new ToDo("", "implements", 12);
        assertTrue(valid.isValid());
    }

    @Test
    public void invalidTodo()
    {
        ToDo valid = new ToDo("", null, 12);
        assertFalse(valid.isValid());
    }

}