package com.charlotte.kies.model;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;
@Component
public class LobsterData {

    @Column(name = "realtime_start")
    private String realtimeStart;

    @Column(name = "realtime_end")
    private String realtimeEnd;

    @Column(name = "observation_start")
    private String observationStart;

    @Column(name = "observation_end")
    private String observationEnd;

    @Column(name = "units")
    private String units;

    @Column(name = "output_type")
    private Long outputType;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "order_by")
    private String orderBy;

    @Column(name = "sort_order")
    private String sortOrder;

    @Column(name = "count")
    private String count;

    @Column(name = "offset")
    private Long offset;

    @Column(name = "limit")
    private Long limit;

    @Column(name = "observations")
    private List<Observation> observations;

    public LobsterData() {
    }

    public LobsterData(String realtimeStart, String realtimeEnd, String observationStart, String observationEnd, String units, Long outputType, String fileType, String orderBy, String sortOrder, String count, Long offset, Long limit, List<Observation> observations) {
        this.realtimeStart = realtimeStart;
        this.realtimeEnd = realtimeEnd;
        this.observationStart = observationStart;
        this.observationEnd = observationEnd;
        this.units = units;
        this.outputType = outputType;
        this.fileType = fileType;
        this.orderBy = orderBy;
        this.sortOrder = sortOrder;
        this.count = count;
        this.offset = offset;
        this.limit = limit;
        this.observations = observations;
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

    public String getObservationStart() {
        return observationStart;
    }

    public void setObservationStart(String observationStart) {
        this.observationStart = observationStart;
    }

    public String getObservationEnd() {
        return observationEnd;
    }

    public void setObservationEnd(String observationEnd) {
        this.observationEnd = observationEnd;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Long getOutputType() {
        return outputType;
    }

    public void setOutputType(Long outputType) {
        this.outputType = outputType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }
}
