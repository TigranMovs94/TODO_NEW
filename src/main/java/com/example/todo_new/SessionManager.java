package com.example.todo_new;

import java.util.HashMap;
import java.util.Map;
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
}
