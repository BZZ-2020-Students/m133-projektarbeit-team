package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.model.Spieler;
import dev.nadina.projektarbeit.model.Team;
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

    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertTeam(
            @FormParam("teamID") String teamID,
            @FormParam("teamname") String teamname,
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

    @Path("delete")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteTeam(
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

    @Path("update")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateTeam(
            @FormParam("teamID") String teamID,
            @FormParam("teamname") String teamname,
            @FormParam("gruendungsdatum") String gruendungsdatum
    ){
        int httpStatus = 200;
        Team team = DataHandler.readTeamByID(teamID);
        if (team != null) {
            team.setTeamID(teamID);
            team.setTeamname(teamname);
            team.setGruendungsdatum(gruendungsdatum);

            DataHandler.updateTeam();
        }else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("Team erfolgreich aktualisiert!")
                .build();
    }


}
