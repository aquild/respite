package dev.aquild.respite.Services.entities;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Pose;
import org.bukkit.util.Vector;

import java.util.UUID;

public class Entity {
    public final UUID uuid;
    public final String name;
    public final Location location;
    public final Vector velocity;
    public final Pose pose;
    public final boolean dead;
    public final boolean onGround;

    public Entity(org.bukkit.entity.Entity entity) {
        this.uuid = entity.getUniqueId();
        this.name = entity.getName();
        this.location = entity.getLocation();
        this.velocity = entity.getVelocity();
        this.pose = entity.getPose();
        this.dead = entity.isDead();
        this.onGround = entity.isOnGround();
    }

    public void update(Server server) {
        org.bukkit.entity.Entity entity = server.getEntity(this.uuid);
        //entity.teleport(this.location);
        entity.setVelocity(velocity);
    }
}
