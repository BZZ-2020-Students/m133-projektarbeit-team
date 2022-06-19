package dev.nadina.projektarbeit.model;

import jakarta.validation.constraints.*;
import jakarta.ws.rs.FormParam;

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
    @FormParam("spartenID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String sportartID;

    @FormParam("sportart")
    @NotEmpty
    @Size(min = 3, max = 40)
    private String sportart;

    @FormParam("spieleranzahl")
    @Max(999)
    @Min(1)
    private Integer spieleranzahl;


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
    public Integer getSpieleranzahl() {
        return spieleranzahl;
    }

    /**
     * @Setter
     * @param spieleranzahl the spieleranzahl to set
     */
    public void setSpieleranzahl(Integer spieleranzahl) {
        this.spieleranzahl = spieleranzahl;
    }
}
