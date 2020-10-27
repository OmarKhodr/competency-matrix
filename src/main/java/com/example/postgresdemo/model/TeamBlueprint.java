package com.example.postgresdemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TeamBlueprint {

    private Demand[] demands;
    private ProjectBlueprint blueprint;

    public Demand[] getDemands() {
        return demands;
    }

    public void setDemands(Demand[] demands) {
        this.demands = demands;
    }

    public ProjectBlueprint getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(ProjectBlueprint blueprint) {
        this.blueprint = blueprint;
    }
}
