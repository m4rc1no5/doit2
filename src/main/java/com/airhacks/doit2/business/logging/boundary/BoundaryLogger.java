package com.airhacks.doit2.business.logging.boundary;

import com.airhacks.doit2.business.monitoring.entity.CallEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class BoundaryLogger {

    @Inject
    Event<CallEvent> monitoring;

    @AroundInvoke
    public Object logCall(InvocationContext ic) throws Exception {

        long start = System.currentTimeMillis();
        try {
            return ic.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;
            monitoring.fire(new CallEvent(ic.getMethod().getName(), duration));
        }

    }

}
