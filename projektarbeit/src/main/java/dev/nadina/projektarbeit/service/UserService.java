package dev.nadina.projektarbeit.service;


import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.model.Team;
import dev.nadina.projektarbeit.model.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * ServiceKlasse für User
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 2.0
 * @since   2022-05-20
 */
@Path("user")
public class UserService {
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @return Response
     * @param teamUUID
     */
    public Response listUser(){
        List<User> UserList = DataHandler.readAllUser();
        return Response
                .status(200)
                .entity(UserList)
                .build();
    }


}
