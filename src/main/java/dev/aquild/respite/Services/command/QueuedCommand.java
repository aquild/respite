package dev.aquild.respite.Services.command;

import org.bukkit.Server;
import org.bukkit.command.CommandException;

import java.util.Objects;
import java.util.UUID;

public class QueuedCommand {
    public final String command;
    public final String executor;
    public boolean errored;

    public QueuedCommand(String command, String executor) {
        this.command = command;
        this.executor = executor;
    }

    public void runCommand(Server server) {
        try {
            if (this.executor == null) {
                server.dispatchCommand(server.getConsoleSender(), this.command);
            } else {
                server.dispatchCommand(Objects.requireNonNull(server.getEntity(UUID.fromString(this.executor))), this.command);
            }
        } catch(CommandException | NullPointerException e) {
            this.errored = true;
        }
    }
}
