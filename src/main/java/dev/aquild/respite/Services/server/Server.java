package dev.aquild.respite.Services.server;

public class Server {
    public final String name;
    public final String motd;
    public final String ip;
    public final int port;
    public final String version;
    public final int viewDistance;
    public final int maxPlayers;
    public final boolean nether;
    public final boolean end;

    public Server(org.bukkit.Server server) {
         this.name = server.getName();
         this.motd = server.getMotd();
         this.ip = server.getIp();
         this.port = server.getPort();
         this.version = server.getVersion();
         this.viewDistance = server.getViewDistance();
         this.maxPlayers = server.getMaxPlayers();
         this.nether = server.getAllowNether();
         this.end = server.getAllowEnd();
    }
}
