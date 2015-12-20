package com.airhacks.doit2.business.reminders.boundary;

import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.ejb.Stateless;
import javax.inject.Inject;
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

    @Path("{id}")
    public TodoResources find(@PathParam("id") long id)
    {
        return new TodoResources(id, manager);
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
