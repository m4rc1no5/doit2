package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TodosResouces {
    @Rule
    public JAXRSClientProvider provider = JAXRSClientProvider.buildWithURI("http://localhost:8080/doit2/api/todos");

    @Test
    public void fetchToDos()
    {
        Response response = this.provider.target().request(MediaType.APPLICATION_JSON).get();
        Assert.assertThat(response.getStatus(), CoreMatchers.is(200));
        String payload = response.readEntity(String.class);
        System.out.println("payload " + payload);
        //Assert.assertTrue(payload.startsWith("Hey"));
    }
}
