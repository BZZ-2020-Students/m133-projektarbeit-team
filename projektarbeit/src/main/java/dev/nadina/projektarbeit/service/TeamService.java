package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.model.Team;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path ("teamprojekt")
public class TeamService {
    @Path("team")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTeam(){
        List<Team> TeamList = DataHandler.getInstance().readAllTeams();
        return Response
                .status(200)
                .entity(TeamList)
                .build();
    }
}
