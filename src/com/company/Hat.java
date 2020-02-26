package com.company;

import org.json.simple.JSONArray;

import java.util.*;

public class Hat {

    private List<Team> teams;

    public Hat() {
        this.teams = new ArrayList<>();
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }
}
