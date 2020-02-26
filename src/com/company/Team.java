package com.company;

public class Team {
    private String name;
    private String country;
    private long points;

    public Team(String name, String country, long points) {
        this.name = name;
        this.country = country;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public long getPoints() {
        return points;
    }
}
