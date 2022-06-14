package dev.nadina.projektarbeit.model;
/**
 * Daten und Methoden für die Sportarten
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 2.0
 * @since   2022-05-20
 */
public class Sportarten {

    /**
     * Attribute
     */
    private String sportartID;
    private String sportart;
    private String spieleranzahl;


    /**
     * Methoden
     */
    public void createSportart(){

    }

    public void readSportart(){

    }

    public void listSportart(){

    }

    public void updateSportart(){

    }

    public void deleteSportart(){

    }

    /**
     * @Getter
     * @return the sportartID
     */
    public String getSportartID() {
        return sportartID;
    }

    /**
     * @Setter
     * @param sportartID the sportartID to set
     */
    public void setSportartID(String sportartID) {
        this.sportartID = sportartID;
    }

    /**
     * @Getter
     * @return the sportart
     */
    public String getSportart() {
        return sportart;
    }

    /**
     * @Setter
     * @param sportart the sportart to set
     */
    public void setSportart(String sportart) {
        this.sportart = sportart;
    }

    /**
     * @Getter
     * @return the spieleranzahl
     */
    public String getSpieleranzahl() {
        return spieleranzahl;
    }

    /**
     * @Setter
     * @param spieleranzahl the spieleranzahl to set
     */
    public void setSpieleranzahl(String spieleranzahl) {
        this.spieleranzahl = spieleranzahl;
    }
}
