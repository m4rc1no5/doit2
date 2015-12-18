package com.airhacks.doit2.business.reminders.boundary;

import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ToDoManager {

    @PersistenceContext
    EntityManager em;

    public ToDo findById(long id) {
        return this.em.find(ToDo.class, id);
    }

    public void delete(long id) {
        try {
            ToDo reference = this.em.getReference(ToDo.class, id);
            this.em.remove(reference);
        } catch (EntityNotFoundException e) {
            //it was deleted before
        }
    }

    public List<ToDo> all() {
        return this.em.createNamedQuery(ToDo.findAll, ToDo.class).getResultList();
    }

    public ToDo save(ToDo todo)
    {
        return this.em.merge(todo);
    }

    public ToDo updateStatus(long id, boolean done) {
        ToDo todo = this.findById(id);
        todo.setDone(done);
        return todo;
    }
}
