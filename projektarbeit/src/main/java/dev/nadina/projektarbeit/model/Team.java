package dev.nadina.projektarbeit.model;

/**
 * Daten für ein Team
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 1.0
 * @since   2022-05-20
 */
public class Team {

    /**
     * Attribute
     */
    private String teamID;
    private String team;
    private String gruendungsdatum;

    /**
     * Methoden
     */
    public void createTeam(){

    }

    public void readTeam(){

    }

    public void listTeam(){

    }

    public void  updateTeam(){

    }

    public void deleteTeam(){

    }

    /**
     * @Getter
     * @return the teamID
     */
    public String getTeamID() {
        return teamID;
    }

    /**
     * @Setter
     * @param teamID the teamID to set
     */
    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    /**
     * @Getter
     * @return the team
     */
    public String getTeam() {
        return team;
    }

    /**
     * @Setter
     * @param team the team to set
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * @Getter
     * @return the gruendungsdatum
     */
    public String getGruendungsdatum() {
        return gruendungsdatum;
    }

    /**
     * @Setter
     * @param gruendungsdatum the gruendungsdatum to set
     */
    public void setGruendungsdatum(String gruendungsdatum) {
        this.gruendungsdatum = gruendungsdatum;
    }

}
