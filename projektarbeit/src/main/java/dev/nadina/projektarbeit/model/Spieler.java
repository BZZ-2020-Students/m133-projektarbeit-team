package dev.nadina.projektarbeit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class Spieler {

    @JsonIgnore
    /*
    * Attribute*/
    private String name;
    private String vorname;
    private String geburtsdatum;
    private Integer spielernr;


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

    //    public static void main(String[] args) {
//        positionhandball pha = positionhandball.Fluegel;
//        System.out.println(pha);
//
//        for (positionhandball enhumha : positionhandball.values()){
//            System.out.println(enhumha);
//        }
//    }
}
