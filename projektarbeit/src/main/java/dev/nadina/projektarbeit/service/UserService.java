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

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @return Response
     * @param userUUID
     */
    public Response getUser(
            @QueryParam("id") String userUUID
    ) {
        int httpStatus = 200;
        User user = DataHandler.readUserByID(userUUID);
        if(user == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(user)
                .build();
    }

    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertUser(
            @FormParam("userUUID") String userUUID,
            @FormParam("userName") String userName,
            @FormParam("password") String password,
            @FormParam("userRole") String userRole

    ) {
        User user = new User();
        user.setUserUUID(userUUID);
        user.setUserName(userName);
        user.setPassword(password);
        user.setUserRole(userRole);

        DataHandler.insertUser(user);

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("User erfolgreich angelegt!")
                .build();
    }

    @Path("delete")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(
            @QueryParam("id") String userUUID
    ){
        int httpStatus = 200;
        if (DataHandler.deleteUser(userUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("User erfolgreich gelöscht")
                .build();
    }

    @Path("update")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(
            @FormParam("userUUID") String userUUID,
            @FormParam("userName") String userName,
            @FormParam("password") String password,
            @FormParam("userRole") String userRole
    ){
        int httpStatus = 200;
        User user = DataHandler.readUserByID(userUUID);
        if (user != null) {
            user.setUserName(userName);
            user.setPassword(password);
            user.setUserRole(userRole);

            DataHandler.updateUser();
        }else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("User erfolgreich aktualisiert!")
                .build();
    }
}
