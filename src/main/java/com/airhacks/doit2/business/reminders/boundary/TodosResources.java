package com.airhacks.doit2.business.reminders.boundary;

import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("todos")
public class TodosResources {

    @Inject
    ToDoManager manager;

    @GET
    @Path("{id}")
    public ToDo find(@PathParam("id") long id)
    {
        return manager.findById(id);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id)
    {
        manager.delete(id);
    }

    @GET
    public List<ToDo> all()
    {
        return this.manager.all();
    }

    @POST
    public void save(ToDo todo)
    {
        System.out.println("todo = " + todo);
    }
}
