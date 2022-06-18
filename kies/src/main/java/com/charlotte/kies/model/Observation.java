package com.charlotte.kies.model;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;

@Component
public class Observation {

    @Column(name = "realtime_start")
    private String realtimeStart;

    @Column(name = "realtime_end")
    private String realtimeEnd;

    @Column(name = "date")
    private String date;

    @Column(name = "value")
    private String value;

    public Observation() {
    }

    public Observation(String realtimeStart, String realtimeEnd, String date, String value) {
        this.realtimeStart = realtimeStart;
        this.realtimeEnd = realtimeEnd;
        this.date = date;
        this.value = value;
    }

    public String getRealtimeStart() {
        return realtimeStart;
    }

    public void setRealtimeStart(String realtimeStart) {
        this.realtimeStart = realtimeStart;
    }

    public String getRealtimeEnd() {
        return realtimeEnd;
    }

    public void setRealtimeEnd(String realtimeEnd) {
        this.realtimeEnd = realtimeEnd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
