package dev.nadina.projektarbeit.model;

import java.time.LocalDate;

public class Spieler {

    /*
    * Attribute*/
    private String name;
    private String vorname;
    private LocalDate geburtsdatum;
    private Integer spielernr;

    private enum positionhandball{
        Fluegel,
        Zweier,
        Mitte,
        Kreis,
        Goalie
    }

    /*
    * Methoden*/
    public void createSpieler(){

    }

    public void readSpieler(){

    }

    public void listSpieler(){

    }

    public void  updateSpieler(){

    }

    public void deleteSpieler(){

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
