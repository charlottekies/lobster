package com.charlotte.kies.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InflationData {

    private List<Observation> observations;

    public InflationData() {
    }

    public InflationData(List<Observation> observations) {
        this.observations = observations;
    }

    /**** Getters and Setters ****/

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }
}
