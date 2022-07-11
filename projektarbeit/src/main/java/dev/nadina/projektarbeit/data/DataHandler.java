package dev.nadina.projektarbeit.data;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.nadina.projektarbeit.model.Spieler;
import dev.nadina.projektarbeit.model.Sportarten;
import dev.nadina.projektarbeit.model.Team;
import dev.nadina.projektarbeit.model.User;
import dev.nadina.projektarbeit.service.Config;
import jakarta.inject.Singleton;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Schreibt und liest die Daten in die JSON-Files
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 2.0
 * @since   2022-05-22
 */

@Singleton
public class DataHandler {
    private static List<Spieler> SpielerList;
    private static List<Team> TeamList;
    private static List<Sportarten> SportartenList;
    private static List<User> UserList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {

    }

    /*================================================================= Spieler =================================================================
     * insertSpieler
     * deleteSpieler
     * updateSpieler
     * readAllSpieler
     * readSpielerByID
     * */

    /**
     * insert Spieler to SpielerList
     */
    public static void insertSpieler(Spieler spieler) {
        getSpielerList().add(spieler);
        writeSpielerJSON();
    }

    /**
     * deletes a spieler identified by the spielerID
     * @param spielerID
     * @return success=true/false
     */
    public static boolean deleteSpieler(String spielerID) {
        Spieler spieler = readSpielerByID(spielerID);
        if (spieler != null) {
            getSpielerList().remove(spieler);
            writeSpielerJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * updates a spieler identified by the spielerID
     */
    public static void updateSpieler(){
        writeSpielerJSON();
    }

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

    /*================================================================= Team =================================================================
     * insertTeam           getTeamList
     * deleteTeam           setTeamList
     * updateTeam
     * readAllTeam
     * readTeamByID
     * */

    /**
     * insert Team to TeamList
     */
    public static void insertTeam(Team team) {
        getTeamList().add(team);
        writeTeamJSON();
    }

    /**
     * deletes a team identified by the teamID
     * @param teamID
     * @return  success=true/false
     */
    public static boolean deleteTeam(String teamID) {
        Team team = readTeamByID(teamID);
        if (team != null) {
            getTeamList().remove(team);
            writeTeamJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * updates a team identified by the teamID
     */
    public static void updateTeam(){
        writeTeamJSON();
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

    public static List<Team> readSortedTeams(
            String sortField,
            String sortOrder,
            String filterField,
            String filter
    ) {
        final Comparator<Team> compareTeams = new Comparator<Team>() {
            @Override
            public int compare(Team team1, Team team2) {
                int result = 0;
                if (sortField.equals("teamname")) {
                    result = team1.getTeamname().compareTo(team2.getTeamname());
                } else if (sortField.equals("gruendungsdatum")) {
                    result = team1.getGruendungsdatum().compareTo(team2.getGruendungsdatum());
                } else if (sortField.equals("teamID")) {
                    result = team1.getTeamID().compareTo(team2.getTeamID());
                }
                if (sortOrder.equals("DESC")) {
                    result *= -1;
                }
                return result;
            }

        };

        List<Team> teams;
        if (filter == null || filter.equals("")) {
            teams = getTeamList();
        } else {
            teams = readFilteredTeams(filterField, filter);
        }
        teams.sort(compareTeams);
        return teams;
    }

    public static List<Team> readFilteredTeams(
            String fieldname,
            String filter)
    {
        Predicate<Team> predicate = null;
        if(fieldname.equals("teamnameFilter")){
            predicate = team -> team.getTeamname().toLowerCase().contains(filter.toLowerCase());
        } else if(fieldname.equals("gruendungsdatumFilter")){
            predicate = team -> team.getGruendungsdatum().toLowerCase().contains(filter.toLowerCase());
        } else if(fieldname.equals("teamIDFilter")){
            predicate = team -> team.getTeamID().toLowerCase().contains(filter.toLowerCase());
        }
        List<Team> filteredList =
                DataHandler.getTeamList().
                stream().
                filter(predicate).
                collect(Collectors.toList());
        return filteredList;
    }



    /**
     * gets TeamList
     * @return value of TeamList
     */
    private static List<Team> getTeamList() {

        if (DataHandler.TeamList == null) {
            DataHandler.setTeamList(new ArrayList<>());
            readTeamJSON();
        }
        return TeamList;
    }

    /**
     * writes the Spielers to the JSON-file
     * @param TeamList the value of TeamList
     */
    private static void setTeamList(List<Team> TeamList) {
        DataHandler.TeamList = TeamList;
    }



    /*================================================================= Sportarten =================================================================
     * insertSportarten
     * deleteSportarten
     * updateSportarten
     * readAllSportarten
     * readSportartenByID
     * */

    /**
     * insert Team to TeamList
     */
    public static void insertSportarten(Sportarten sportarten) {
        getSportartenList().add(sportarten);
        writeSportartenJSON();
    }

    /**
     * deletes a sportarten identified by the sportartenID
     * @param sportartID
     * @return  success=true/false
     */
    public static boolean deleteSportart (String sportartID) {
        Sportarten sportarten = readSportartByID(sportartID);
        if (sportarten != null) {
            getSportartenList().remove(sportarten);
            writeSportartenJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * updates a sportarten identified by the sportartenID
     */
    public static void updateSportart(){
        writeSportartenJSON();
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


    /*================================================================= User =================================================================
     * insertUser
     * deleteUser
     * updateUser
     * readAllUser
     * readUserByID
     * */

    /**
     * insert User to UserList
     */
    public static void insertUser(User user) {
        getUserList().add(user);
        writeUserJSON();
    }

    /**
     * deletes a user identified by the userID
     * @param userID
     * @return  success=true/false
     */
    public static boolean deleteUser(String userID) {
        User user = readUserByID(userID);
        if (user != null) {
            getUserList().remove(user);
            writeUserJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * updates a user identified by the userID
     */
    public static void updateUser() {
        writeUserJSON();
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

    /*================================================================= WriteJSON =================================================================
     * writeSpielerJSON,        readSpielerJSON
     * writeTeamJSON,           readTeamJSON
     * writeSportartenJSON,     readSportartenJSON
     * writeUserJSON,           readUserJSON
     * */

    /**
     * writes SpielerList to Spieler.json
     */
    private static void writeSpielerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("SpielerJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getSpielerList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
     * writes TeamList to Team.json
     */
    private static void writeTeamJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("TeamJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getTeamList());
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
     * writes Sportarten to Sportarten.json
     */
    private static void writeSportartenJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("SportartJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getSportartenList());
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

    private static void writeUserJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("UserJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter,getUserList());
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
     * sets SpielerList
     * @return  value of SpielerList
     */
    private static List<Spieler> getSpielerList() {

        if (DataHandler.SpielerList == null) {
            DataHandler.setSpielerList(new ArrayList<>());
            readSpielerJSON();
        }
        return SpielerList;
    }

    /**
     * sets SpielerList
     * @param SpielerList the value to set
     */
    private static void setSpielerList(List<Spieler> SpielerList) {
        DataHandler.SpielerList = SpielerList;
    }

    /**
     * gets SportartenList
     * @return value of SportartenList
     */
    private static List<Sportarten> getSportartenList() {
        if (DataHandler.SportartenList == null) {
            DataHandler.setSportartenList(new ArrayList<>());
            readSportartenJSON();
        }
        return SportartenList;
    }

    /**
     * sets SportartenList
     * @param SportartenList the value to set
     */
    private static void setSportartenList(List<Sportarten> SportartenList) {
        DataHandler.SportartenList = SportartenList;
    }

    /**
     * gets UserList
     * @return value of UserList
     */
    private static List<User> getUserList() {
        if (DataHandler.UserList == null) {
            DataHandler.setUserList(new ArrayList<>());
            readUserJSON();
        }
        return UserList;
    }

    /**
     * sets UserList
     * @param UserList the value to set
     */
    private static void setUserList(List<User> UserList) {
        DataHandler.UserList = UserList;
    }
}
