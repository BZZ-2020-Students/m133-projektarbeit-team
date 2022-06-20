package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.model.Spieler;
import dev.nadina.projektarbeit.model.Team;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * ServiceKlasse für die Team
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 2.0
 * @since   2022-05-20
 */

@Path ("team")
public class TeamService {
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @return Response
     * @param String teamID
     */
    public Response listTeam(){
        List<Team> TeamList = DataHandler.readAllTeams();
        return Response
                .status(200)
                .entity(TeamList)
                .build();
    }

    /**
     * @param teamID
     * @return Response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @return Response
     * @param String teamID
     */
    public Response readSpieler(
            @QueryParam("id") String teamID
    ){
        int httpStatus = 200;
        Team team = DataHandler.readTeamByID(teamID);
        if(team == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(team)
                .build();
    }

    /**
     * @param teamID
     * @param teamname
     * @param gruendungsdatum
     * @return Response
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertTeam(
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("teamID") String teamID,

            @NotEmpty
            @Size(min = 3, max = 40)
            @FormParam("teamname") String teamname,

            @NotEmpty
            @FormParam("gruendungsdatum") String gruendungsdatum

    ) {
        Team team = new Team();
        team.setTeamID(teamID);
        team.setTeamname(teamname);
        team.setGruendungsdatum(gruendungsdatum);

        DataHandler.insertTeam(team);

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Team erfolgreich angelegt!")
                .build();
    }

    /**
     * @param teamID
     * @return Response
     */
    @Path("delete")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteTeam(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("id") String teamID
    ){
        int httpStatus = 200;
        if (DataHandler.deleteTeam(teamID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("Team erfolgreich gelöscht")
                .build();
    }

    /**
     * @param t
     * @return Response
     */
    @Path("update")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateTeam(
            @Valid @BeanParam Team t
    ){
        Team team = DataHandler.readTeamByID(t.getTeamID());
        team.setTeamID(t.getTeamID());
        team.setTeamname(t.getTeamname());
        team.setGruendungsdatum(t.getGruendungsdatum());

        DataHandler.updateTeam();


        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Team erfolgreich aktualisiert!")
                .build();
    }

}
