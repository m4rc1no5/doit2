package com.airhacks.doit2.business.monitoring.boundary;

import com.airhacks.doit2.business.monitoring.entity.CallEvent;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.rpc.Call;
import java.util.List;

@Stateless
@Path("boundary-invocations")
public class BoundaryInvocationsResource {

    @Inject
    MonitorSink ms;

    @GET
    public List<CallEvent> expose() {
        return ms.getResentEvents();
    }
}
