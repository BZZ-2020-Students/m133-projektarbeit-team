package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.model.Spieler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * ServiceKlasse für Spieler
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 1.0
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
        List<Spieler> SpielerList = DataHandler.getInstance().readAllSpielers();
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
        Spieler spieler = DataHandler.getInstance().readSpielerByID(spielerID);
        if(spieler == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(spieler)
                .build();
    }


}
