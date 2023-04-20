package ru.schukina.spring.restApp.config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class VKServiceAppConfig {
    public static final int APP_ID = 1234567; // ID VK приложения
    public static final String APP_SECRET = "abcdefg"; // Секретный ключ VK приложения
    public static final String REDIRECT_URI = "http://localhost:8080"; // URL-адрес приложения

    String username = "your_username";
    String password = "your_password";
    String authString = username + ":" + password;
    String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());

    // Добавляем заголовок "Authorization" в HTTP-запрос
    HttpURLConnection connection;

    {
        try {
            connection = (HttpURLConnection) new URL(REDIRECT_URI).openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    connection.("Authorization", "Basic " + encodedAuthString);

}
