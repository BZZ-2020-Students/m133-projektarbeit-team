package dev.nadina.projektarbeit.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.nadina.projektarbeit.model.Spieler;
import dev.nadina.projektarbeit.model.Sportarten;
import dev.nadina.projektarbeit.model.Team;
import dev.nadina.projektarbeit.model.User;
import dev.nadina.projektarbeit.service.Config;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Schreibt und liest die Daten in die JSON-Files
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 1.0
 * @since   2022-05-22
 */

@Singleton
public class DataHandler {
    private static DataHandler instance = null;
    private static List<Spieler> SpielerList;
    private static List<Team> TeamList;
    private static List<Sportarten> SportartenList;
    private static List<User> UserList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
//        setTeamList(new ArrayList<>());
//        readTeamJSON();
//        setSpielerList(new ArrayList<>());
//        readSpielerJSON();
//        setSportartenList(new ArrayList<>());
//        readSportartenJSON();
//        setUserList(new ArrayList<>());
//        readUserJSON();
    }

//    /**
//     * gets the only instance of this class
//     * @return instance
//     */
//    public static DataHandler getInstance() {
//        if (instance == null)
//            instance = new DataHandler();
//        return instance;
//    }

    /**
     * reads all Spielers
     * @return list of Spielers
     */
    public static List<Spieler> readAllSpielers() {
        return getSpielerList();
    }

    /**
     * reads all SpielerByID
     * @return spieler
     */
    public static Spieler readSpielerByID(String spielerID) {
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
     * @return list of Sportarten
     */
    public static List<Sportarten> readAllSportarten() {
        return getSportartenList();
    }

    /**
     * reads all SportartenByID
     * @return sportart
     */
    public static Sportarten readSportartByID(String sportartID) {
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
    public static List<Team> readAllTeams() {
        return getTeamList();
    }


    /**
     * reads all TeamsByID
     * @return team
     */
    public static Team readTeamByID(String teamID) {
        Team team = null;
        for (Team entity : getTeamList()) {
            if (entity.getTeamID().equals(teamID)) {
                team = entity;
            }
        }
        return team;
    }

    /**
     * reads all Users
     * @return list of Users
     */
    public static List<User> readAllUser(){
        return getUserList();
    }

    /**
     * reads all UsersByID
     * @return user
     */
    public static User readUserByID(String userID) {
        User user = null;
        for (User entity : getUserList()) {
            if (entity.getUserUUID().equals(userID)) {
                user = entity;
            }
        }
        return user;
    }

    /**
     * reads the Spielers from the JSON-file
     */
    private static void readSpielerJSON() {
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
    private static void readTeamJSON() {
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

    /**
     * reads the Sportarten from the JSON-file
     */
    private static void readSportartenJSON() {
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

    /**
     * reads the User from the JSON-file
     */
    private static void readUserJSON() {
        try {
            String path = Config.getProperty("UserJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets TeamList
     *
     * @return value of TeamList
     */
    private static List<Team> getTeamList() {
        return TeamList;
    }

    /**
     * writes the Spielers to the JSON-file
     *
     * @param TeamList the value of TeamList
     */
    private void setTeamList(List<Team> TeamList) {
        this.TeamList = TeamList;
    }


    /**
     * sets SpielerList
     *
     * @return  value of SpielerList
     */
    private static List<Spieler> getSpielerList() {
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

    /**
     * gets SportartenList
     *
     * @return value of SportartenList
     */
    private static List<Sportarten> getSportartenList() {
        return SportartenList;
    }

    /**
     * sets SportartenList
     *
     * @param SportartenList the value to set
     */
    private void setSportartenList(List<Sportarten> SportartenList) {
        this.SportartenList = SportartenList;
    }

    /**
     * gets UserList
     *
     * @return value of UserList
     */
    private static List<User> getUserList() {
        return UserList;
    }

    /**
     * sets UserList
     *
     * @param UserList the value to set
     */
    private void setUserList(List<User> UserList) {
        this.UserList = UserList;
    }
}
