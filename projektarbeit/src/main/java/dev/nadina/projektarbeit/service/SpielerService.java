package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.model.Spieler;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;

/**
 * ServiceKlasse für Spieler
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 2.0
 * @since   2022-05-20
 */


@Path("spieler")
public class SpielerService {
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     *  @return Response
     *  @param String spielerID
     */
    public Response listSpieler(){
        List<Spieler> SpielerList = DataHandler.readAllSpielers();
        return Response
                .status(200)
                .entity(SpielerList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     *  @return Response
     *  @param String spieleID
     */
    public Response readSpieler(
            @QueryParam("id") String spielerID
    ){
        int httpStatus = 200;
        Spieler spieler = DataHandler.readSpielerByID(spielerID);
        if(spieler == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(spieler)
                .build();
    }

    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSpieler(
            @FormParam("spielerID") String spielerID,
            @FormParam("name") String name,
            @FormParam("vorname") String vorname,
            @FormParam("geburtsdatum") String geburtsdatum,
            @FormParam("position") String position,
            @FormParam("spielernr") Integer spielernr,
            @FormParam("captain") Boolean captain
    ) {
        Spieler spieler =  new Spieler();
        spieler.setSpielerID(spielerID);
        spieler.setName(name);
        spieler.setVorname(vorname);
        spieler.setGeburtsdatum(geburtsdatum);
        spieler.setPosition(position);
        spieler.setSpielernr(spielernr);
        spieler.setCaptain(captain);

        DataHandler.insertSpieler(spieler);

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Spieler wurde erfolgreich hinzugefügt")
                .build();
    }

    @Path("delete")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSpieler(
            @QueryParam("id") String spielerID
    ){
        int httpStatus = 200;
        if (DataHandler.deleteSpieler(spielerID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("Spieler erfolgreich gelöscht")
                .build();
    }

    @Path("update")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSpieler(
            @FormParam("spielerID") String spielerID,
            @FormParam("name") String name,
            @FormParam("vorname") String vorname,
            @FormParam("geburtsdatum") String geburtsdatum,
            @FormParam("position") String position,
            @FormParam("spielernr") Integer spielernr,
            @FormParam("captain") Boolean captain
    ){
        int httpStatus = 200;
        Spieler spieler = DataHandler.readSpielerByID(spielerID);
        if (spieler != null) {
            spieler.setSpielerID(spielerID);
            spieler.setName(name);
            spieler.setVorname(vorname);
            spieler.setGeburtsdatum(geburtsdatum);
            spieler.setPosition(position);
            spieler.setSpielernr(spielernr);
            spieler.setCaptain(captain);

            DataHandler.updateSpieler();
        }else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("Spieler erfolgreich aktualisiert")
                .build();
    }
}
