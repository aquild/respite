package dev.aquild.respite.Services.entities;

import org.bukkit.GameMode;

public class Player extends Entity {
    public final String displayName;
    public final GameMode gameMode;
    public final double health;
    public final int food;
    public final float exp;
    public final long firstPlayed;

    public Player(org.bukkit.entity.Player player) {
        super(player);
        this.displayName = player.getDisplayName();
        this.gameMode = player.getGameMode();
        this.health = player.getHealth();
        this.food = player.getFoodLevel();
        this.exp = player.getExp();
        this.firstPlayed = player.getFirstPlayed();
    }
}
