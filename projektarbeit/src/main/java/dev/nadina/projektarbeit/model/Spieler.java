package dev.nadina.projektarbeit.model;

import jakarta.validation.constraints.*;
import jakarta.ws.rs.FormParam;

/**
 * Daten und Methoden für einen Spieler
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 2.0
 * @since   2022-05-20
 */
public class Spieler {
    /**
     * Attribute
     */

    /**
     * @FormParam("spielerID")
     * @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
     */
    @FormParam("spielerID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String spielerID;

    /**
     * @FormParam("spielername")
     * @NotEmpty
     * @Size(min = 3, max = 40)
     */
    @FormParam("name")
    @NotEmpty
    @Size(min = 3, max = 40)
    private String name;

    /**
     * @FormParam("vorname")
     * @NotEmpty
     * @Size(min = 3, max = 40)
     */
    @FormParam("vorname")
    @NotEmpty
    @Size(min = 3, max = 40)
    private String vorname;

    /**
     * @FormParam("geburtstag")
     * @NotEmpty
     */
    @FormParam("geburtsdatum")
    @NotEmpty
    private String geburtsdatum;

    /**
     * @FormParam("spielernr")
     * @NotEmpty
     * @Max(999)
     * @Min(1)
     */
    @FormParam("spielernr")
    @Max(999)
    @Min(1)
    private Integer spielernr;

    /**
     * @FormParam("position")
     * @NotEmpty
     * @Size(min = 3, max = 50)
     */
    @FormParam("position")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String position;

    /**
     * @FormParam("captain")
     * @NotNull
     */
    @FormParam("captain")
    @NotNull
    private Boolean captain;


    /**
     * Methoden
     */
    public void createSpieler(){

    }

    public void readSpieler(){

    }

    public void listSpieler(){

    }

    public void updateSpieler(){

    }

    public void deleteSpieler(){

    }


    /**
     * @Getter
     * @return the spielerID
     */
    public String getSpielerID() {
        return spielerID;
    }

    /**
     * @Setter
     * @param spielerID the spielerID to set
     */
    public void setSpielerID(String spielerID) {
        this.spielerID = spielerID;
    }

    /**
     * @Getter
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @Setter
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @Getter
     * @return the vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * @Setter
     * @param vorname the vorname to set
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * @Getter
     * @return the geburtsdatum
     */
    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * @Setter
     * @param geburtsdatum the geburtsdatum to set
     */
    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    /**
     * @Getter
     * @return the spielernr
     */
    public Integer getSpielernr() {
        return spielernr;
    }

    /**
     * @Setter
     * @param spielernr the spielernr to set
     */
    public void setSpielernr(Integer spielernr) {
        this.spielernr = spielernr;
    }

    /**
     * @Getter
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @Setter
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @Getter
     * @return the captain
     */
    public Boolean getCaptain() {
        return captain;
    }

    /**
     * @Setter
     * @param captain the captain to set
     */
    public void setCaptain(Boolean captain) {
        this.captain = captain;
    }
}
