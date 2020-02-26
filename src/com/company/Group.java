package com.company;

import java.util.*;

public class Group {

    private List<Team> teams;

    public Group() {
        this.teams = new ArrayList<>();
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }
}
