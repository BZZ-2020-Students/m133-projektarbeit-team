package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.model.Sportarten;
import dev.nadina.projektarbeit.model.Team;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
/**
 * ServiceKlasse für Sportarten
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 1.0
 * @since   2022-05-20
 */
@Path ("sportarten")
public class SportartenService {
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @return Response
     * @param String sportartenID
     */
    public Response listSportart(){
        List<Sportarten> SportartList = DataHandler.readAllSportarten();
        return Response
                .status(200)
                .entity(SportartList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @return Response
     * @param String sportartenID
     */
    public Response readSpieler(
            @QueryParam("id") String sportartID
    ){
        int httpStatus = 200;
        Sportarten sportart = DataHandler.readSportartByID(sportartID);
        if(sportart == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(sportart)
                .build();
    }

    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSportarten(
            @FormParam("sportartID") String sportartID,
            @FormParam("sportart") String sportart,
            @FormParam("spieleranzahl") String spieleranzahl

    ) {
        Sportarten sa = new Sportarten();
        sa.setSportartID(sportartID);
        sa.setSportart(sportart);
        sa.setSpieleranzahl(spieleranzahl);

        DataHandler.insertSportarten(sa);

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Sportarten erfolgreich angelegt!")
                .build();
    }
}
