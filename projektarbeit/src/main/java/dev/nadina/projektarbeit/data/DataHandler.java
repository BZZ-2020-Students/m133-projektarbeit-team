package dev.nadina.projektarbeit.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.nadina.projektarbeit.model.Spieler;
import dev.nadina.projektarbeit.service.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Spieler> SpielerList;
   // private List<Publisher> publisherList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setSpielerList(new ArrayList<>());
        readSpielerJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all Spielers
     * @return list of Spielers
     */
    public List<Spieler> readAllSpielers() {
        return getSpielerList();
    }

    /**
     * reads the Spielers from the JSON-file
     */
    private void readSpielerJSON() {
        try {
            String path = Config.getProperty("SpielerJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Spieler[] spielers = objectMapper.readValue(jsonData, Spieler[].class);
            for (Spieler spieler : spielers) {
                getSpielerList().add(spieler);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets SpielerList
     *
     * @return value of SpielerList
     */
    private List<Spieler> getSpielerList() {
        return SpielerList;
    }

    /**
     * sets SpielerList
     *
     * @param SpielerList the value to set
     */
    private void setSpielerList(List<Spieler> SpielerList) {
        this.SpielerList = SpielerList;
    }

}