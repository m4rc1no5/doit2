package com.airhacks.doit2.business.reminders.boundary;

import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ToDoManager {

    public ToDo findById(long id) {
        return new ToDo("implement REST endpoint " + id, "...", 100);
    }

    public void delete(long id) {
        System.out.println("deleted = " + id);
    }

    public List<ToDo> all() {
        List<ToDo> all = new ArrayList<>();
        all.add(findById(42));
        return all;
    }

    public void save(ToDo todo)
    {
        System.out.println("saving = " + todo);
    }
}
