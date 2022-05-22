package dev.nadina.projektarbeit.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.nadina.projektarbeit.data.DataHandler;

public class Spieler {

    private String spielerID;
    private String name;
    private String vorname;
    private String geburtsdatum;
    private Integer spielernr;
    private String position;
    private Boolean captain;


    /*
    * Methoden*/
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


    public String getSpielerID() {
        return spielerID;
    }

    public void setSpielerID(String spielerID) {
        this.spielerID = spielerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Integer getSpielernr() {
        return spielernr;
    }

    public void setSpielernr(Integer spielernr) {
        this.spielernr = spielernr;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getCaptain() {
        return captain;
    }

    public void setCaptain(Boolean captain) {
        this.captain = captain;
    }




}
