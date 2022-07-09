package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.data.UserData;
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
    @Path("login")
    @POST
    @Produces(MediaType.TEXT_PLAIN)

    public Response login(
            @FormParam("userName") String userName,
            @FormParam("password") String password) {

        int httpStatus;

        User user = UserData.findUser(userName, password);

        if(user == null || user.getUserRole() == null || user.getUserRole().equals("gast")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }
        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();

        return response;
    }


    @Path("logout")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response logoff() {

        Response response = Response
                .status(200)
                .entity("")
                .build();

        return response;
    }

}
