package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.model.Team;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
/**
 * ServiceKlasse für die Team
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 1.0
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
}
