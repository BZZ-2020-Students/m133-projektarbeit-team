package dev.nadina.projektarbeit.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.nadina.projektarbeit.model.Spieler;
import dev.nadina.projektarbeit.model.Sportarten;
import dev.nadina.projektarbeit.model.Team;
import dev.nadina.projektarbeit.service.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Spieler> SpielerList;
    private List<Team> TeamList;
    private List<Sportarten> SportartenList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setTeamList(new ArrayList<>());
        readTeamJSON();
        setSpielerList(new ArrayList<>());
        readSpielerJSON();
        setSportartenList(new ArrayList<>());
        readSportartenJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    /******************* Read Methodes ******************/
    /**
     * reads all Spielers
     * @return list of Spielers
     */
    public List<Spieler> readAllSpielers() {
        return getSpielerList();
    }

    public Spieler readSpielerByID(String spielerID) {
        Spieler spieler = null;
        for (Spieler entity : getSpielerList()) {
            if (entity.getSpielerID().equals(spielerID)) {
                spieler = entity;
            }
        }
        return spieler;
    }


    /**
     * reads all Sportarten
     * @return list of Spotarten
     */
    public List<Sportarten> readAllSportarten() {
        return getSportartenList();
    }

    public Sportarten readSportartByID(String sportartID) {
        Sportarten sportart = null;
        for (Sportarten entity : getSportartenList()) {
            if (entity.getSportartID().equals(sportartID)) {
                sportart = entity;
            }
        }
        return sportart;
    }

    /**
     * reads all Teams
     * @return list of Teams
     */
    public List<Team> readAllTeams() {
        return getTeamList();
    }

    public Team readTeamByID(String teamID) {
        Team team = null;
        for (Team entity : getTeamList()) {
            if (entity.getTeamID().equals(teamID)) {
                team = entity;
            }
        }
        return team;
    }


    /******************* Read JSON Files ******************/
    /**
     * reads the Spielers from the JSON-file
     */
    private void readSpielerJSON() {
        try {
            String path = Config.getProperty("SpielerJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Spieler[] spielers = objectMapper.readValue(jsonData, Spieler[].class);
            for (Spieler spieler : spielers) {
                getSpielerList().add(spieler);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the Teams from the JSON-file
     */
    private void readTeamJSON() {
        try {
            String path = Config.getProperty("TeamJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Team[] teams = objectMapper.readValue(jsonData, Team[].class);
            for (Team team : teams) {
                getTeamList().add(team);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readSportartenJSON() {
        try {
            String path = Config.getProperty("SportartJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Sportarten[] sportarts = objectMapper.readValue(jsonData, Sportarten[].class);
            for (Sportarten sportart : sportarts) {
                getSportartenList().add(sportart);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /******************* Lists ******************/


    private List<Team> getTeamList() {
        return TeamList;
    }


    private void setTeamList(List<Team> TeamList) {
        this.TeamList = TeamList;
    }


    /**
     * gets SpielerList
     *
     * @return value of SpielerList
     */
    private List<Spieler> getSpielerList() {
        return SpielerList;
    }

    /**
     * sets SpielerList
     *
     * @param SpielerList the value to set
     */
    private void setSpielerList(List<Spieler> SpielerList) {
        this.SpielerList = SpielerList;
    }


    private List<Sportarten> getSportartenList() {
        return SportartenList;
    }

    private void setSportartenList(List<Sportarten> SportartenList) {
        this.SportartenList = SportartenList;
    }
}
