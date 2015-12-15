package com.airhacks.doit2.business.reminders.boundary;

import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ToDoManager {

    @PersistenceContext
    EntityManager em;

    public ToDo findById(long id) {
        return this.em.find(ToDo.class, id);
    }

    public void delete(long id) {
        ToDo reference = this.em.getReference(ToDo.class, id);
        this.em.remove(reference);
    }

    public List<ToDo> all() {
        return this.em.createNamedQuery(ToDo.findAll, ToDo.class).getResultList();
    }

    public void save(ToDo todo)
    {
        this.em.merge(todo);
    }
}
