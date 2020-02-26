package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Loterie {

    protected int nbGroup;
    protected int nbTeam;
    protected boolean countryLimit;
    protected List<Hat> hats;
    protected List<Group> groups;
    protected ArrayList<String> teamSelected;

    public Loterie(int nbGroup, int nbTeam, boolean countryLimit) {
        this.nbGroup = nbGroup;
        this.nbTeam = nbTeam;
        this.countryLimit = countryLimit;
        this.hats = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.teamSelected = new ArrayList<>();
    }

    public void createHat(JSONArray teamsList) {
        for (int j = 0; j < getNbHat(); j++) {
            Hat hat = new Hat();
            for (int i = 0; i < getNbTeamByHat(); i++) {
                JSONObject team = (JSONObject) teamsList.get(i + (j * getNbTeamByHat()));
                hat.addTeam(this.parseTeam((JSONObject) team));
            }
            this.addHat(hat);
        }
        this.createGroup();

        for (int i = 0; i < this.groups.size(); i++) {
            List<Team> listTeamInHat = this.groups.get(i).getTeams();
            System.out.println("Groupe " + (i + 1));
            for (int y = 0; y < listTeamInHat.size(); y++) {
                System.out.println("Équipe: " + listTeamInHat.get(y).getName() + " Pays: " + listTeamInHat.get(y).getCountry() + " Coefficient: " + listTeamInHat.get(y).getPoints());
            }
        }
//        this.checkHat();
    }

    private void checkHat() {
        for (int i = 0; i < this.hats.size(); i++) {
            List<Team> listTeamInHat = this.hats.get(i).getTeams();
            for (int y = 0; y < listTeamInHat.size(); y++) {
                System.out.println(listTeamInHat.get(y).getName());
            }
        }
    }

    public void createGroup() {
        for (int j = 0; j < nbGroup; j++) {
            Group group = selectTeam();
            this.addGroup(group);
        }
    }

    private Group selectTeam() {
        Group group = new Group();
        Random rand = new Random();
        ArrayList<String> country = new ArrayList<>();
        for (int i = 0; i < this.hats.size(); i++) {
            List<Team> allTeamHat = this.hats.get(i).getTeams();
            Team randomElement = allTeamHat.get(rand.nextInt(allTeamHat.size()));

            if (country.contains(randomElement.getCountry()) && countryLimit) {
                List<Team> allTeamHatW = this.hats.get(i).getTeams();
                randomElement = allTeamHatW.get(rand.nextInt(allTeamHatW.size()));
                int lookup = 0;
                boolean killLoop = true;
                while ((country.contains(randomElement.getCountry())) && killLoop) {
                    randomElement = allTeamHatW.get(rand.nextInt(allTeamHatW.size()));
                    lookup++;
                    if (lookup > 50 && this.teamSelected.size() > (nbTeam - nbGroup)) {
                        killLoop = false;
                    }
                }
            }
            group.addTeam(randomElement);

            this.hats.get(i).getTeams().remove(randomElement);
            this.teamSelected.add(randomElement.getName());
            country.add(i, randomElement.getCountry());
        }

        return group;

    }

    private Team parseTeam(JSONObject team) {
        return new Team((String) team.get("name"), (String) team.get("country"), (long) team.get("point"));
    }

    public void addHat(Hat hat) {
        this.hats.add(hat);
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public int getTeamByGroup() {
        return this.nbTeam / this.nbGroup;
    }

    public int getNbHat() {
        return this.nbTeam / this.nbGroup;
    }

    public int getNbTeamByHat() {
        return this.nbTeam / this.getNbHat();
    }
}
