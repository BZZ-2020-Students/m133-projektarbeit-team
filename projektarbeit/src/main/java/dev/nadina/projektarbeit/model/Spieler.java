package dev.nadina.projektarbeit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.nadina.projektarbeit.data.DataHandler;

/**
 * @Beschreibung
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 1.0
 * @since   2022-05-20
 */
public class Spieler {

    /**
     * Attribute
     */
    private String spielerID;
    private String name;
    private String vorname;
    private String geburtsdatum;
    private Integer spielernr;
    private String position;
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
