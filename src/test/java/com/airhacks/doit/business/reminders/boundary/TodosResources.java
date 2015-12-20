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

        //object
        JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
        JsonObject todoToCreate = todoBuilder.
                add("caption", "implement").
                add("priority", 3).
                build();

        //create
        Response postResponse = this.provider.target().request().post(Entity.json(todoToCreate));
        Assert.assertThat(postResponse.getStatus(), CoreMatchers.is(201));
        String location = postResponse.getHeaderString("Location");
        System.out.println(location);

        //find
        JsonObject dedicatedTodo = this.provider.client().target(location).request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        Assert.assertTrue(dedicatedTodo.getString("caption").contains("implement"));

        //update
        JsonObjectBuilder updateBuilder = Json.createObjectBuilder();
        JsonObject updated = updateBuilder.
                add("caption", "implemented").
                build();
        Response updateResponse = this.provider.client().target(location).request(MediaType.APPLICATION_JSON).put(Entity.json(updated));
        Assert.assertThat(updateResponse.getStatus(), CoreMatchers.is(200));

        //update again
        updateBuilder = Json.createObjectBuilder();
        updated = updateBuilder.
                add("caption", "implemented").
                add("priority", 100).
                build();
        updateResponse = this.provider.client().target(location).request(MediaType.APPLICATION_JSON).put(Entity.json(updated));
        Assert.assertThat(updateResponse.getStatus(), CoreMatchers.is(409));
        String cause = updateResponse.getHeaderString("cause");
        Assert.assertNotNull(cause);
        System.out.println(cause);

        //find it again
        JsonObject updatedTodo = this.provider.client().target(location).request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        Assert.assertTrue(updatedTodo.getString("caption").contains("implemented"));

        //update status
        JsonObjectBuilder statusBuilder = Json.createObjectBuilder();
        JsonObject status = statusBuilder.
                add("done", true).
                build();
        this.provider.client().target(location).path("status").request(MediaType.APPLICATION_JSON).put(Entity.json(status));

        //verify status
        updatedTodo = this.provider.client().target(location).request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        System.out.println(updatedTodo);
        Assert.assertThat(updatedTodo.getBoolean("done"), CoreMatchers.is(true));

        //update not existing status
        JsonObjectBuilder notExistingBuilder = Json.createObjectBuilder();
        status = notExistingBuilder.
                add("done", true).
                build();
        Response responseNotExistingStatus = this.provider.target().path("-42").path("status").request(MediaType.APPLICATION_JSON).put(Entity.json(status));
        Assert.assertThat(responseNotExistingStatus.getStatus(), CoreMatchers.is(400));
        Assert.assertFalse(responseNotExistingStatus.getHeaderString("reason").isEmpty());

        //update malformed status
        JsonObjectBuilder malformedBuilder = Json.createObjectBuilder();
        status = malformedBuilder.
                add("kabooom", true).
                build();
        Response responsemMalformedStatus = this.provider.client().target(location).path("status").request(MediaType.APPLICATION_JSON).put(Entity.json(status));
        Assert.assertThat(responsemMalformedStatus.getStatus(), CoreMatchers.is(400));
        Assert.assertFalse(responsemMalformedStatus.getHeaderString("reason").isEmpty());

        //findAll
        Response response = this.provider.target().request(MediaType.APPLICATION_JSON).get();
        Assert.assertThat(response.getStatus(), CoreMatchers.is(200));
        JsonArray allTodos = response.readEntity(JsonArray.class);
        System.out.println("allTodos " + allTodos);
        Assert.assertFalse(allTodos.isEmpty());

        JsonObject todo = allTodos.getJsonObject(0);
        Assert.assertTrue(todo.getString("caption").startsWith("imp"));

        //delete not-existing
        Response deleteResponse = this.provider.target().path("42").request(MediaType.APPLICATION_JSON).delete();
        Assert.assertThat(deleteResponse.getStatus(), CoreMatchers.is(204)); //status 204 ma void
    }

    @Test
    public void createToDoWithoutCaption()
    {
        //object
        JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
        JsonObject todoToCreate = todoBuilder.
                add("priority", 42).
                build();

        //create
        Response postResponse = this.provider.target().request().post(Entity.json(todoToCreate));
        Assert.assertThat(postResponse.getStatus(), CoreMatchers.is(400));
        postResponse.getHeaders().entrySet().forEach(System.out::println);
    }

    @Test
    public void createValidToDo()
    {
        //object
        JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
        JsonObject todoToCreate = todoBuilder.
                add("caption", "some caption").
                add("priority", 10).
                build();

        //create
        Response postResponse = this.provider.target().request().post(Entity.json(todoToCreate));
        Assert.assertThat(postResponse.getStatus(), CoreMatchers.is(201));
    }

    @Test
    public void createToDoWithHighPriorityWithoutDescription()
    {
        //object
        JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
        JsonObject todoToCreate = todoBuilder.
                add("caption", "kaboom").
                add("priority", 12).
                build();

        //create
        Response postResponse = this.provider.target().request().post(Entity.json(todoToCreate));
        Assert.assertThat(postResponse.getStatus(), CoreMatchers.is(400));
        postResponse.getHeaders().entrySet().forEach(System.out::println);
    }
}
