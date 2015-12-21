package com.airhacks.doit2.business.logging.boundary;

@FunctionalInterface
public interface LogSink {

    void log(String msg);

}
