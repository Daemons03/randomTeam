package com.company;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        Loterie loterie = new Loterie(4, 64, false);
        loterie.createHat(getTeam(64));
    }

    public static JSONArray getTeam(int nbTeam) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("/home/maxime/site/test/loterie/ressources/realTeam.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray allTeam = (JSONArray) obj;

            JSONArray selectedTeam = new JSONArray();

            AtomicInteger i = new AtomicInteger(0);
            allTeam.forEach(team -> {
                if (i.get() < nbTeam){
                    selectedTeam.add(team);
                }
                i.getAndIncrement();
            });

            return selectedTeam;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
