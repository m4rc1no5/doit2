package com.airhacks.doit2.business.reminders.boundary;

import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("todos")
public class TodosResources {

    @GET
    @Path("{id}")
    public ToDo find(@PathParam("id") long id)
    {
        return new ToDo("implement REST endpoint " + id, "...", 100);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id)
    {
        System.out.println("deleted = " + id);
    }

    @GET
    public List<ToDo> all()
    {
        List<ToDo> all = new ArrayList<>();
        all.add(find(42));
        return all;
    }

    @POST
    public void save(ToDo todo)
    {
        System.out.println("todo = " + todo);
    }
}
