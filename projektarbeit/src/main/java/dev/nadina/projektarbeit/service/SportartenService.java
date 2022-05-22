package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.model.Sportarten;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path ("sportarten")
public class SportartenService {
    @Path("sportart")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSportart(){
        List<Sportarten> SportartList = DataHandler.getInstance().readAllSportarten();
        return Response
                .status(200)
                .entity(SportartList)
                .build();
    }
}
