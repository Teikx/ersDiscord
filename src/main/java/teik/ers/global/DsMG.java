package teik.ers.global;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import teik.ers.bukkit.ErsDiscord;
import teik.ers.global.models.Report;

import java.awt.*;
import java.util.function.Function;

public class DsMG {

    private final JDA jda;
    private boolean img_active;
    private String serverID, channelID, image;
    private String title, reportedDataTitle, reportedDataDescription,
            reporterDataTitle, reporterDataDescription, reason, date, status;
    private final String FOOTER = "EpicReports • Discord Bot • @Teikx";

    public DsMG(ErsDiscord plugin) {
        this.jda = plugin.getJda();
        loadConfigData(plugin.discordManager::get);
    }

    public DsMG(teik.ers.bungee.ErsDiscord plugin) {
        this.jda = plugin.getJda();
        loadConfigData(plugin.discordFile::get);
    }

    private void loadConfigData(Function<String, String> configGetter) {
        this.serverID = configGetter.apply("serverID");
        this.channelID = configGetter.apply("channelID");
        this.img_active = Boolean.parseBoolean(configGetter.apply("img_active"));
        this.image = configGetter.apply("image");

        this.title = configGetter.apply("title");
        this.reportedDataTitle = configGetter.apply("reportedDataTitle");
        this.reportedDataDescription = configGetter.apply("reportedDataDescription");
        this.reporterDataTitle = configGetter.apply("reporterDataTitle");
        this.reporterDataDescription = configGetter.apply("reporterDataDescription");
        this.reason = configGetter.apply("reason");
        this.date = configGetter.apply("date");
        this.status = configGetter.apply("status");
    }

    public void sendDiscordMessage(Report report) {
        Guild guild = jda.getGuildById(serverID);
        if (guild == null) {
            logError("504", "serverID");
            return;
        }

        TextChannel channel = guild.getTextChannelById(channelID);
        if (channel == null) {
            logError("505", "channelID");
            return;
        }

        EmbedBuilder embed = buildEmbed(report);
        channel.sendMessageEmbeds(embed.build()).queue();
    }

    private EmbedBuilder buildEmbed(Report report) {
        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("📝 " + title.replace("%reportID%", String.valueOf(report.getReportID())));
        embed.setColor(Color.GREEN);
        embed.setTimestamp(report.getDateTime().atZone(java.time.ZoneId.systemDefault()).toInstant());
        embed.setFooter(FOOTER, null);

        embed.addField("\u200B", "\u200B", false);

        embed.addField("🎯 " + reportedDataTitle,
                format(report, reportedDataDescription, true), false);

        embed.addField("\u200B", "\u200B", false);

        embed.addField("🗣️ " + reporterDataTitle,
                format(report, reporterDataDescription, false), false);

        embed.addField("\u200B", "\u200B", false);

        embed.addField("📄 " + reason, report.getReason(), false);
        embed.addField("\u200B", "\u200B", false);

        embed.addField("📅 " + date, report.getDate(), true);
        embed.addField("🔄 " + status, report.getProcess().toString(), true);

        if(img_active){
            embed.setImage(image);
        }

        return embed;
    }

    private String format(Report r, String template, boolean isReported) {
        return template
                .replace("%" + (isReported ? "reportedName" : "reporterName") + "%", isReported ? r.getReportedName() : r.getReporterName())
                .replace("%" + (isReported ? "reportedUUID" : "reporterUUID") + "%", isReported ? r.getReportedUUID() : r.getReporterUUID())
                .replace("%" + (isReported ? "reportedGamemode" : "reporterGamemode") + "%", isReported ? r.getReportedGamemode() : r.getReporterGamemode())
                .replace("%" + (isReported ? "reportedHealth" : "reporterHealth") + "%", String.valueOf(isReported ? r.getReportedHealth() : r.getReporterHealth()))
                .replace("%" + (isReported ? "reportedServer" : "reporterServer") + "%", isReported ? r.getReportedServer() : r.getReporterServer())
                .replace("%" + (isReported ? "reportedLocation" : "reporterLocation") + "%", isReported ? r.getReportedLocation() : r.getReporterLocation())
                .replace("%" + (isReported ? "reportedIP" : "reporterIP") + "%", isReported ? r.getReportedIP() : r.getReporterIP());
    }

    private void logError(String code, String configKey) {
        System.out.println(String.format("[ErsDiscord] Error %s: Could not connect using %s. Check your discord-config.yml!", code, configKey));
    }
}
