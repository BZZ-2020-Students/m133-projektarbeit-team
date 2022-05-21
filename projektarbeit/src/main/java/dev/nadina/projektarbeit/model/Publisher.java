package dev.nadina.projektarbeit.model;

import java.util.List;

public class Publisher {

    private String publisherUUID;
    private String publisher;
    public List<Spieler> SpielerList;


    public List<Spieler> getSpielerList() {
        return SpielerList;
    }

    public void setSpielerList(List<Spieler> spielerList) {
        this.SpielerList = spielerList;
    }

}
