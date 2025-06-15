package teik.ers.bungee;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.internal.utils.JDALogger;
import net.md_5.bungee.api.plugin.Plugin;
import teik.ers.bungee.config.DiscordFile;
import teik.ers.global.DsConnection;
import teik.ers.global.DsMG;
import teik.ers.global.models.Report;

import java.io.IOException;

public class ErsDiscord extends Plugin {

    public DiscordFile discordFile;
    private DsConnection dsConnection;

    private JDA jda;
    private boolean isDiscordActive;

    private DsMG dsMG;

    @Override
    public void onEnable() {
        JDALogger.setFallbackLoggerEnabled(false);
        try {
            discordFile = new DiscordFile(this);
        } catch (IOException ignored) {}
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
