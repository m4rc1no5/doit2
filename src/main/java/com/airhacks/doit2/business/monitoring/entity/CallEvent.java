package com.airhacks.doit2.business.monitoring.entity;

public class CallEvent {

    private String methodName;
    private long duration;

    public CallEvent(String methodName, long duration) {
        this.methodName = methodName;
        this.duration = duration;
    }

    public String getMethodName() {
        return methodName;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "CallEvent{" +
                "duration=" + duration +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
