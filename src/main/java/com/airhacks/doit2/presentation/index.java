package com.airhacks.doit2.presentation;

import com.airhacks.doit2.business.reminders.boundary.ToDoManager;
import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class Index {

    @Inject
    ToDoManager boundary;

    ToDo todo;

    @PostConstruct
    public void init() {
        this.todo = new ToDo();
    }

    public ToDo getTodo() {
        return todo;
    }

    public Object save() {
        this.boundary.save(todo);
        return null;
    }

}
