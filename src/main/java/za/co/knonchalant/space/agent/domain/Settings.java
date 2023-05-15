package za.co.knonchalant.space.agent.domain;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Settings {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static Settings get(String location) throws IOException {
        return new Gson().fromJson(Files.readString(Path.of(location)), Settings.class);
    }
    public void persist(String location) throws IOException {
        Files.writeString(Path.of(location), new Gson().toJson(this));
    }
}
