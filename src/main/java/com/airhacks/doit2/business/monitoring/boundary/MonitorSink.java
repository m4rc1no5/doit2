package com.airhacks.doit2.business.monitoring.boundary;

import com.airhacks.doit2.business.logging.boundary.LogSink;
import com.airhacks.doit2.business.monitoring.entity.CallEvent;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.xml.rpc.Call;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MonitorSink {

    @Inject
    LogSink LOG;

    CopyOnWriteArrayList<CallEvent> recentEvents;

    @PostConstruct
    public void init() {
        this.recentEvents = new CopyOnWriteArrayList<>();
    }

    public void onCallEvent(@Observes CallEvent event) {
        LOG.log(event.toString());
        this.recentEvents.add(event);
    }

    public List<CallEvent> getResentEvents() {
        return this.recentEvents;
    }

    public LongSummaryStatistics getStatistics() {
        return this.recentEvents.stream().collect(Collectors.summarizingLong(CallEvent::getDuration));
    }
}
