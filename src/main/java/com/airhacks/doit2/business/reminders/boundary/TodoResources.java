package com.airhacks.doit2.business.reminders.boundary;

import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public class TodoResources {

    private long id;
    private ToDoManager manager;

    public TodoResources(long id, ToDoManager manager) {
        this.id = id;
        this.manager = manager;
    }

    @GET
    public ToDo find()
    {
        return manager.findById(id);
    }

    @DELETE
    public void delete()
    {
        manager.delete(id);
    }

    @PUT
    public ToDo update(ToDo todo)
    {
        todo.setId(id);
        return manager.save(todo);
    }

    @PUT
    @Path("/status")
    public Response statusUpdate(JsonObject statusUpdate)
    {
        if(! statusUpdate.containsKey("done")){
            return Response.status(Response.Status.BAD_REQUEST).header("reason", "JSON should contains field done").build();
        }
        boolean done = statusUpdate.getBoolean("done");
        ToDo todo = manager.updateStatus(id, done);
        if(todo == null){
            return Response.status(Response.Status.BAD_REQUEST).header("reason", "todo with id " + id + " does not exists").build();
        } else {
            return Response.ok(todo).build();
        }
    }
}
