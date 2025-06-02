package teik.ers.global;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import teik.ers.bukkit.ErsDiscord;

public class DsConnection {
    private JDA jda;

    private final String discordToken;
    private static final String BOT_ACTIVITY_URL = "https://www.spigotmc.org/resources/112351/";
    private boolean isDiscordActive;

    public DsConnection(ErsDiscord pl){
        this.discordToken = pl.discordManager.getBotToken();
        boolean initialized = initializeBot();
        if(initialized) {
            Bukkit.getConsoleSender().sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            "&b[&fEpicReports&b]&5 Discord Bot&a connected!")

            );
            return;
        }
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&',
                        "&b[&fEpicReports&b]&c Error 503: Error connecting to Discord bot. Contact the developer.")
        );
    }

    public DsConnection(teik.ers.bungee.ErsDiscord plugin) {
        this.discordToken = plugin.discordFile.getBotToken();
        boolean initialized = initializeBot();
        if(initialized) {
            plugin.getLogger().info(
                    net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&',
                            "&b[&fEpicReports&b]&5 Discord Bot&a connected!")
            );
            return;
        }
        plugin.getLogger().info(
                net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&',
                        "&b[&fEpicReports&b]&c Error 503: Error connecting to Discord bot. Contact the developer.")
        );
    }

    private boolean initializeBot() {
        try {
            jda = JDABuilder
                    .createLight(discordToken)
                    .setActivity(Activity.streaming("EpicReports", BOT_ACTIVITY_URL))
                    .build();

            isDiscordActive = true;
            return true;
        } catch (Exception e) {
            isDiscordActive = false;
            jda = null;
            return false;
        }
    }

    public JDA getJda() {
        return jda;
    }

    public boolean isDiscordActive() {
        return isDiscordActive;
    }
}
