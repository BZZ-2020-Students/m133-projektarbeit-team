package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.data.UserData;
import dev.nadina.projektarbeit.model.Team;
import dev.nadina.projektarbeit.model.User;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ServiceKlasse für User
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 2.0
 * @since   2022-05-20
 */

@Path("user")
public class UserService {
    @PermitAll
    @Path("login")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        int httpStatus = 200;
        User user = UserData.findUser(
                username,
                password
        );

        String token = "";
        Map<String, Object> claimMap = new HashMap<>();
        int randomWord = 0;
        if (user.getUserRole().equals("guest")) {
            httpStatus = 404;
        } else {
            randomWord = (int) (Math.random() * 5);
            claimMap.put("role", user.getUserRole());
//            claimMap.put("word", user.getWords().get(randomWord));
        }
//        token = JWToken.buildToken(user.getUserRole(), 5, claimMap);


        NewCookie roleCookie = new NewCookie(
                "userRole",
                user.getUserRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        NewCookie wordCookie = new NewCookie(
                "secret",
                randomWord + 1 + "",
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity(randomWord + 1)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .cookie(roleCookie)
                .cookie(wordCookie)
                .build();
    }
}
