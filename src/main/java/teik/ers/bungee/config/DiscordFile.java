package teik.ers.bungee.config;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import teik.ers.bungee.ErsDiscord;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class DiscordFile {
    File discordFile;
    Configuration discord;

    ErsDiscord plugin;

    private boolean img_active;
    private String botToken, serverID, channelID;
    private String title, reportedDataTitle, reportedDataDescription, reporterDataTitle,
            reporterDataDescription, reason, date, status, image;

    public DiscordFile(ErsDiscord plugin) throws IOException {
        this.plugin = plugin;
        reloadDiscord();
    }

    public File getFile(){
        return discordFile;
    }
    public Configuration getDiscord() {
        return discord;
    }

    public void reloadDiscord() throws IOException {
        registerConfig();
        loadConfig();
    }

    private void registerConfig() throws IOException {
        try {
            makeConfig();
        }catch (Exception e) {
            makeConfigAlternative();
        }
    }

    private void makeConfig() throws IOException {
        discordFile = new File(plugin.getDataFolder(), "discord-config.yml");

        if (!discordFile.exists()) {
            try (InputStream in = plugin.getResourceAsStream("discord-config.yml")) {
                Files.copy(in, discordFile.toPath());
            }
        }
        discord = ConfigurationProvider.getProvider(YamlConfiguration.class).load(discordFile);
    }
    private void makeConfigAlternative() throws IOException {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        discordFile = new File(plugin.getDataFolder(), "discord-config.yml");

        if (!discordFile.exists()) {
            try (InputStream in = plugin.getResourceAsStream("discord-config.yml")) {
                Files.copy(in, discordFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        discord = ConfigurationProvider.getProvider(YamlConfiguration.class).load(discordFile);
    }

    public void loadConfig() {
        this.img_active = discord.getBoolean("Discord_img.active");
        this.botToken = discord.getString("Discord_Settings.Discord.botToken");
        this.serverID = discord.getString("Discord_Settings.Discord.serverID");
        this.channelID = discord.getString("Discord_Settings.Discord.channelID");
        this.title = discord.getString("Discord_Msg.Title");
        this.reportedDataTitle = discord.getString("Discord_Msg.reported_data.Title");
        this.reportedDataDescription = discord.getString("Discord_Msg.reported_data.Description");
        this.reporterDataTitle = discord.getString("Discord_Msg.reporter_data.Title");
        this.reporterDataDescription = discord.getString("Discord_Msg.reporter_data.Description");
        this.reason = discord.getString("Discord_Msg.reason");
        this.date = discord.getString("Discord_Msg.date");
        this.status = discord.getString("Discord_Msg.status");
        this.image = discord.getString("Discord_Settings.Discord.discord_image");
    }

    public String get(String key) {
        switch (key.toLowerCase()) {
            case "serverid": return getServerID();
            case "channelid": return getChannelID();
            case "img_active": return String.valueOf(isImg_active());
            case "image": return getImage();
            case "title": return getTitle();
            case "reporteddatatitle": return getReportedDataTitle();
            case "reporteddatadescription": return getReportedDataDescription();
            case "reporterdatatitle": return getReporterDataTitle();
            case "reporterdatadescription": return getReporterDataDescription();
            case "reason": return getReason();
            case "date": return getDate();
            case "status": return getStatus();
            default: throw new IllegalArgumentException("Unknown key in DiscordFile: " + key);
        }
    }

    public boolean isImg_active() {
        return img_active;
    }

    public String getBotToken() {
        return botToken;
    }

    public String getServerID() {
        return serverID;
    }

    public String getChannelID() {
        return channelID;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getReportedDataTitle() {
        return reportedDataTitle;
    }

    public String getReportedDataDescription() {
        return reportedDataDescription;
    }

    public String getReporterDataTitle() {
        return reporterDataTitle;
    }

    public String getReporterDataDescription() {
        return reporterDataDescription;
    }

    public String getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}
