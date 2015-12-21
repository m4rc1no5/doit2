package com.airhacks.doit2.business.logging.boundary;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class BoundaryLogger {

    @Inject
    LogSink LOG;

    @AroundInvoke
    public Object logCall(InvocationContext ic) throws Exception {
        LOG.log("--" + ic.getMethod());
        return ic.proceed();
    }

}
