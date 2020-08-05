package dev.aquild.respite;

import dev.aquild.respite.Services.command.CommandService;
import dev.aquild.respite.Services.entities.EntityService;
import dev.aquild.respite.Services.server.ServerService;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static spark.Spark.*;

public final class Respite extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Respite Enabled");

        this.saveDefaultConfig();

        this.configureWeb();
    }

    public void configureWeb() {
        // Set listener port
        port(getConfig().getInt("port"));

        // Configure authentication
        if (getConfig().getBoolean("authenticated")) {
            before((req, res) -> {
                List<String> tokens = getConfig().getStringList("tokens");
                boolean authenticated = tokens.contains(req.headers("X-Api-Token"));

                if (!authenticated) {
                    halt(401, "Invalid authentication token");
                }
            });
        } else {
            getLogger().warning("API is not authenticated. Enable authentication in config.yml or ANYONE WILL BE ABLE TO ACCESS THE API.");
        }
        ;

        // Attach services and routes
        get("/status", (req, res) -> "API Working");

        // Command service
        path("/command", () -> {
            new CommandService(this).attachRoutes();
        });

        // Entity service
        path("/entity", () -> {
            new EntityService(this).attachRoutes();
        });

        // Server service
        path("/server", () -> {
            new ServerService(this).attachRoutes();
        });
    }
}
