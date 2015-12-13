package com.airhacks.doit2.business.reminders.boundary;

import com.airhacks.doit2.business.reminders.entity.ToDo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by marcinos on 2015-12-07.
 */
@Path("todos")
public class TodosResources {

    @GET
    public ToDo hello()
    {
        return new ToDo("implement REST endpoint", "...", 100);
    }
}
