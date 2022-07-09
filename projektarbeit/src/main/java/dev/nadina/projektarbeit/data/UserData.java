package dev.nadina.projektarbeit.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.nadina.projektarbeit.model.User;
import dev.nadina.projektarbeit.service.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * DataHandler um die Userdaten zu lesen
 *
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 2.0
 * @since   2022-07-09
 */

public class UserData {
    private static final UserData instance = new UserData();

    /**
     * Findet Benutzer durch den Username und das Passwort
     * @param userName
     * @param password
     * @return User
     */
    public static User findUser(String userName, String password) {
        User user = new User();
        List<User> userList = readJson();

        for (User entry : userList) {
            if (entry.getUserName().equals(userName) && entry.getPassword().equals(password)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * Liest das Json File als eine Liste von User Objekten
     * @return  Liste von User Objekten
     */
    private static List<User> readJson() {
        List<User> userList = new ArrayList<>();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("UserJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
