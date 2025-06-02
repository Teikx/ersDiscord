package teik.ers.bukkit;

import net.dv8tion.jda.api.JDA;
import org.bukkit.plugin.java.JavaPlugin;
import teik.ers.bukkit.config.DiscordManager;
import teik.ers.global.DsConnection;
import teik.ers.global.DsMG;
import teik.ers.global.models.Report;

public class ErsDiscord extends JavaPlugin {

    public DiscordManager discordManager;
    private DsConnection dsConnection;

    private JDA jda;
    private boolean isDiscordActive;

    private DsMG dsMG;

    @Override
    public void onEnable() {
        discordManager = new DiscordManager(this);
        dsConnection = new DsConnection(this);
        jda = dsConnection.getJda();
        isDiscordActive = dsConnection.isDiscordActive();

        if(!isDiscordActive) return;
        dsMG = new DsMG(this);
    }

    @Override
    public void onDisable() {

    }

    public DsMG getDsMG() {
        return dsMG;
    }

    public boolean isDiscordActive() {
        return isDiscordActive;
    }

    public void sendDiscordMessage(Report report) {
        dsMG.sendDiscordMessage(report);
    }

    public JDA getJda() {
        return jda;
    }

}
