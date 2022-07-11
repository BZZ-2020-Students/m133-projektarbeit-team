package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.model.Sportarten;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
/**
 * ServiceKlasse für Sportarten
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 2.0
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

    /**
     * @param sportartID
     * @param sportart
     * @param spieleranzahl
     * @return Response
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSportarten(
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("sportartID") String sportartID,

            @NotEmpty
            @Size(min = 3, max = 40)
            @FormParam("sportart") String sportart,

            @Max(999)
            @Min(1)
            @NotNull
            @FormParam("spieleranzahl") Integer spieleranzahl

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

    /**
     * @param teamID
     * @return Response
     */
    @Path("delete")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSporarten(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("id") String teamID
    ){
        int httpStatus = 200;
        if (DataHandler.deleteSportart(teamID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("Team erfolgreich gelöscht")
                .build();
    }

    /**
     * @param sarex
     * @return Response
     */
    @Path("update")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSporart(
            @Valid @BeanParam Sportarten sarex
    ){
        Sportarten sa = DataHandler.readSportartByID(sarex.getSportartID());
        sa.setSportartID(sarex.getSportartID());
        sa.setSportart(sarex.getSportart());
        sa.setSpieleranzahl(sarex.getSpieleranzahl());

        DataHandler.updateSportart();
        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Sportart erfolgreich aktualisiert")
                .build();
    }
}
