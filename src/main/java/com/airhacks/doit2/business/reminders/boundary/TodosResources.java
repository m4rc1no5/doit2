package com.airhacks.doit2.business.reminders.boundary;

import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("todos")
public class TodosResources {

    @GET
    public ToDo hello()
    {
        return new ToDo("caption", "...", 100);
        //return "test";
    }
}
