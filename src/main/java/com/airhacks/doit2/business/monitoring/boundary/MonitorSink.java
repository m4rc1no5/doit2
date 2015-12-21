package com.airhacks.doit2.business.monitoring.boundary;

import com.airhacks.doit2.business.logging.boundary.LogSink;
import com.airhacks.doit2.business.monitoring.entity.CallEvent;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MonitorSink {

    @Inject
    LogSink LOG;

    public void onCallEvent(@Observes CallEvent event) {
        LOG.log(event.toString());
    }

}
