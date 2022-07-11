package dev.nadina.projektarbeit.service;

import dev.nadina.projektarbeit.data.DataHandler;
import dev.nadina.projektarbeit.data.UserData;
import dev.nadina.projektarbeit.model.Team;
import dev.nadina.projektarbeit.model.User;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

        Map<String, Object> claimMap = new HashMap<>();
        int randomInt = 0;
        if (user.getUserRole().equals("guest")) {
            httpStatus = 404;
        } else {
            Random r = new Random();
            randomInt = r.nextInt(99)+1;
            claimMap.put("role", user.getUserRole());
        }

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
                randomInt+"",
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity(randomInt)
                .cookie(wordCookie)
                .cookie(roleCookie)
                .build();
    }

    @PermitAll
    @Path("2fa")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkWord(
            @FormParam("secret") String secret,
            @CookieParam("secret") Cookie cookie
    ) {
        int httpStatus = 200;
        String word = cookie.getValue();
        System.out.println("SecretCookie: "+word);
        System.out.println("SecretForm: "+secret);
        if (word == null || !word.equals(secret)) {
            httpStatus = 401;
        }

        return Response
                .status(httpStatus)
                .entity(null)
                .build();
    }
}
