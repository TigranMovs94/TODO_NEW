package com.example.todo_new;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class SessionManager {
    private static SessionManager instance;
    private Map<String, String> sessionMap;

    SessionManager() {
        sessionMap = new HashMap<>();
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public synchronized String createSession(String username) {
        String token = UUID.randomUUID().toString();
        sessionMap.put(token, username);
        return token;
    }

    public synchronized boolean isValidSession(String token) {
        return sessionMap.containsKey(token);
    }

    public synchronized String getUsernameFromToken(String token) {
        return sessionMap.get(token);
    }

    public synchronized void endSession(String token) {
        sessionMap.remove(token);
    }

    public  static String generateSessionToken() {
        // Generate a unique session token using a random number generator
        Random random = new Random();
        long token = Math.abs(random.nextLong());

        return Long.toString(token, 16);
    }

    public static void saveSessionToDatabase(String username, String sessionToken) {
        // Insert a new record into the sessions table
        try {
            DBcon connect = new DBcon();
            Connection connection = connect.connect("todo");
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO sessions (username, token, created_at) VALUES (?, ?, NOW())");
            statement.setString(1, username);
            statement.setString(2, sessionToken);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
