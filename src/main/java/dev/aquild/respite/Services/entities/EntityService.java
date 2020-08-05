package dev.aquild.respite.Services.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import dev.aquild.respite.ErrorResponse;
import dev.aquild.respite.Respite;
import dev.aquild.respite.Serialization.LocationSerializer;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static spark.Spark.get;

public class EntityService {
    private final Respite plugin;

    public EntityService(Respite plugin) {
        this.plugin = plugin;
    }

    public void attachRoutes() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Location.class, new LocationSerializer())
                .setPrettyPrinting()
                .create();

        // Entities

        get("/:uuid", ((request, response) -> {
            try {
                Entity entity = new Entity(Objects.requireNonNull(this.plugin.getServer().getEntity(UUID.fromString(request.params(":uuid")))));
                return gson.toJson(entity);
            } catch (NullPointerException | IllegalArgumentException | JsonParseException e) {
                response.status(404);
                return gson.toJson(new ErrorResponse(e));
            }
        }));

        get("/player/list",((request, response) -> {
            ArrayList<Player> players = new ArrayList<Player>();

            for(org.bukkit.entity.Player player : this.plugin.getServer().getOnlinePlayers()){
                players.add(new Player(player));
            }

            return gson.toJson(players);
        }));

        get("/player/:uuid", ((request, response) -> {
            try {
                Player player = new Player(Objects.requireNonNull(this.plugin.getServer().getPlayer(UUID.fromString(request.params(":uuid")))));
                return gson.toJson(player);
            } catch (NullPointerException | IllegalArgumentException | JsonParseException e) {
                response.status(404);
                return gson.toJson(new ErrorResponse(e));
            }
        }));
    }
}
