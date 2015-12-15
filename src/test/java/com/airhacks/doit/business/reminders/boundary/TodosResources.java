package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TodosResources {
    @Rule
    public JAXRSClientProvider provider = JAXRSClientProvider.buildWithURI("http://localhost:8080/doit2/api/todos");

    @Test
    public void crud()
    {

        JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
        JsonObject todoToCreate = todoBuilder.
                add("caption", "implement").
                add("priotity", 42).
                build();
        this.provider.target().request().post(Entity.json(todoToCreate));

        Response response = this.provider.target().request(MediaType.APPLICATION_JSON).get();
        Assert.assertThat(response.getStatus(), CoreMatchers.is(200));
        JsonArray allTodos = response.readEntity(JsonArray.class);
        System.out.println("allTodos " + allTodos);
        Assert.assertFalse(allTodos.isEmpty());

        JsonObject todo = allTodos.getJsonObject(0);
        Assert.assertTrue(todo.getString("caption").startsWith("imp"));

        //GET with id
        JsonObject dedicatedTodo = this.provider.target().path("42").request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        Assert.assertTrue(dedicatedTodo.getString("caption").contains("42"));

        Response deleteResponse = this.provider.target().path("42").request(MediaType.APPLICATION_JSON).delete();
        Assert.assertThat(deleteResponse.getStatus(), CoreMatchers.is(204)); //status 204 ma void
    }
}
