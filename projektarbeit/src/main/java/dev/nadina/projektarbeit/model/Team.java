package dev.nadina.projektarbeit.model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;

/**
 * Daten für ein Team
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 2.0
 * @since   2022-05-20
 */

public class Team {

    /**
     * @FormParam("teamID")
     * @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
     */
    @FormParam("teamID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String teamID;

    /**
     * @FormParam("teamname")
     * @NotEmpty
     * @Size(min = 3, max = 40)
     */
    @FormParam("teamname")
    @NotEmpty
    @Size(min = 3, max = 40)
    private String teamname;

    /**
     * @FormParam("gruendungsdatum")
     * @NotEmpty
     */
    @FormParam("gruendungsdatum")
    @NotEmpty
    private String gruendungsdatum;


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
     * @return the teamname
     */
    public String getTeamname() {
        return teamname;
    }

    /**
     * @Setter
     * @param teamname the team to set
     */
    public void setTeamname(String teamname) {
        this.teamname = teamname;
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
