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
        ToDo valid = new ToDo("caption", "implements", 12);
        assertTrue(valid.isValid());
    }

    @Test
    public void invalidTodo()
    {
        ToDo valid = new ToDo("some caption", null, 12);
        assertFalse(valid.isValid());
    }

    @Test
    public void invalidTodoLowPriority()
    {
        ToDo valid = new ToDo("sample caption", null, 10);
        assertTrue(valid.isValid());
    }
}