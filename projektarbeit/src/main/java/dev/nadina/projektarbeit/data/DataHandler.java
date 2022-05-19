package dev.nadina.projektarbeit.data;

import dev.nadina.projektarbeit.model.Spieler;
import sun.security.krb5.Config;

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
    private List<Spieler> spielerList;
    // private List<Publisher> publisherList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setPublisherList(new ArrayList<Spieler>());
        readPublisherJSON();
        setSpielerList(new ArrayList<Spieler>());
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
     * reads a Spieler by its uuid
     * @param SpielerUUID
     * @return the Spieler (null=not found)
     */
    public Spieler readSpielerByUUID(String SpielerUUID) {
        Spieler Spieler = null;
        for (Spieler entry : getSpielerList()) {
            if (entry.getSpielerUUID().equals(SpielerUUID)) {
                Spieler = entry;
            }
        }
        return Spieler;
    }

    /**
     * reads all Publishers
     * @return list of publishers
     */
    public List<Publisher> readAllPublishers() {

        return getPublisherList();
    }

    /**
     * reads a publisher by its uuid
     * @param publisherUUID
     * @return the Publisher (null=not found)
     */
    public Publisher readPublisherByUUID(String publisherUUID) {
        Publisher publisher = null;
        for (Publisher entry : getPublisherList()) {
            if (entry.getPublisherUUID().equals(publisherUUID)) {
                publisher = entry;
            }
        }
        return publisher;
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
            Spieler[] Spielers = objectMapper.readValue(jsonData, Spieler[].class);
            for (Spieler Spieler : Spielers) {
                getSpielerList().add(Spieler);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the publishers from the JSON-file
     */
    private void readPublisherJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("publisherJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Publisher[] publishers = objectMapper.readValue(jsonData, Publisher[].class);
            for (Publisher publisher : publishers) {
                getPublisherList().add(publisher);
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
    private void setSpielerList(ArrayList<Spieler> SpielerList) {
        this.SpielerList = SpielerList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */
    private List<Publisher> getPublisherList() {
        return publisherList;
    }

    /**
     * sets publisherList
     *
     * @param publisherList the value to set
     */
    private void setPublisherList(ArrayList<Spieler> publisherList) {
        this.publisherList = publisherList;
    }


}