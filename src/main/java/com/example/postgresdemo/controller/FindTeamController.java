package com.example.postgresdemo.controller;

import com.example.postgresdemo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FindTeamController {

    @Autowired
    private CompetencyAlgorithm algorithm;

    @PostMapping("/projects/findteam")
    public List<List<Employee>> findTeams(@RequestBody TeamBlueprint team) {
        Demand[] demands = team.getDemands();
        ProjectBlueprint blueprint = team.getBlueprint();
        return algorithm.findTeams(demands, blueprint);
    }


}
