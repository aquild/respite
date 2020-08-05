package dev.aquild.respite.Services.command;

import com.google.gson.Gson;
import dev.aquild.respite.Respite;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Type;

import static spark.Spark.post;

public class CommandService {
    private final Respite plugin;

    public CommandService(Respite plugin) {
        this.plugin = plugin;
    }

    public void attachRoutes() {
        post("/run", ((request, response) -> {
            QueuedCommand command = new Gson().fromJson(request.body(), (Type) QueuedCommand.class);
            new BukkitRunnable() {
                @Override
                public void run() {
                    command.runCommand(plugin.getServer());
                }
            }.runTaskLater(plugin, 0);
            return new Gson().toJson(command);
        }));
    }
}
