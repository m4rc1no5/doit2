package com.airhacks.doit2.business.reminders.boundary;

import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
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

    @PUT
    @Path("{id}")
    public ToDo update(@PathParam("id") long id, ToDo todo)
    {
        todo.setId(id);
        return manager.save(todo);
    }

    @PUT
    @Path("{id}/status")
    public Response statusUpdate(@PathParam("id") long id, JsonObject statusUpdate)
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

    @GET
    public List<ToDo> all()
    {
        return this.manager.all();
    }

    @POST
    public Response save(ToDo todo, @Context UriInfo info)
    {
        ToDo saved = this.manager.save(todo);
        long id = saved.getId();
        URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).build();
    }
}
