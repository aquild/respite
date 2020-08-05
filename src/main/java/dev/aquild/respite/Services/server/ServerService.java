package dev.aquild.respite.Services.server;

import com.google.gson.Gson;
import dev.aquild.respite.Respite;

import static spark.Spark.get;

public class ServerService {
    private final Respite plugin;

    public ServerService(Respite plugin) {
        this.plugin = plugin;
    }

    public void attachRoutes() {
        get("", ((request, response) -> new Gson().toJson(new Server(this.plugin.getServer()))));
    }
}