package com.airhacks.doit2.business.reminders.boundary;

import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ToDoManager {

    public ToDo findById(long id) {
        //return this.em.find(ToDo.class, id);
        return new ToDo("implement REST point " + id, "...", 100);
    }

    public void delete(long id) {
        //ToDo reference = this.em.getReference(ToDo.class, id);
        //this.em.remove(reference);
    }

    public List<ToDo> all() {
        //return this.em.createNamedQuery(ToDo.findAll, ToDo.class).getResultList();
        List<ToDo> all = new ArrayList<>();
        all.add(findById(42));
        return all;
    }

    public void save(ToDo todo)
    {
        //this.em.merge(todo);
    }
}
