package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.model.Spieler;
import dev.nadina.projektarbeit.model.Team;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    /**
     * @param spielerID
     * @return Response
     */
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

    /**
     * @param spielerID
     * @param name
     * @param vorname
     * @param geburtsdatum
     * @param position
     * @param spielernr
     * @param captain
     * @return Response
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSpieler(
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("spielerID") String spielerID,

            @NotEmpty
            @Size(min = 3, max = 40)
            @FormParam("name") String name,

            @NotEmpty
            @Size(min = 3, max = 40)
            @FormParam("vorname") String vorname,

            @NotEmpty
            @FormParam("geburtsdatum") String geburtsdatum,

            @NotEmpty
            @Size(min = 3, max = 50)
            @FormParam("position") String position,

            @Max(999)
            @Min(0)
            @FormParam("spielernr") Integer spielernr,

            @NotNull
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

    /**
     * @param spielerID
     * @return Response
     */
    @Path("delete")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSpieler(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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

    /**
     * @param s
     * @return Response
     */
    @Path("update")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSpieler(
            @Valid @BeanParam Spieler s
    ){

        Spieler spieler = DataHandler.readSpielerByID(s.getSpielerID());
        spieler.setSpielerID(s.getSpielerID());
        spieler.setName(s.getName());
        spieler.setVorname(s.getVorname());
        spieler.setGeburtsdatum(s.getGeburtsdatum());
        spieler.setPosition(s.getPosition());
        spieler.setSpielernr(s.getSpielernr());
        spieler.setCaptain(s.getCaptain());

        DataHandler.updateSpieler();
        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Team erfolgreich aktualisiert!")
                .build();
    }
}

