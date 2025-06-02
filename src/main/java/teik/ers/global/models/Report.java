package teik.ers.global.models;

import java.time.LocalDateTime;

public class Report {
    private final String Reason, Date;
    private String
            ReporterUUID,
            ReportedUUID,
            ReporterName,
            ReportedName,
            ReporterLocation,
            ReportedLocation,
            ReporterServer,
            ReportedServer,
            ReportedGamemode,
            ReporterGamemode,
            Process,
            ReporterIP,
            ReportedIP;
    private int ReportedHealth, ReporterHealth, reportID;
    private LocalDateTime dateTime;

    public Report(String reason, String date, String reporterUUID, String reportedUUID, String reporterName, String reportedName, String reporterLocation, String reportedLocation, String reporterServer, String reportedServer, String reportedGamemode, String reporterGamemode, String process, String reporterIP, String reportedIP, int reportedHealth, int reporterHealth, int reportID, LocalDateTime dateTime) {
        Reason = reason;
        Date = date;
        ReporterUUID = reporterUUID;
        ReportedUUID = reportedUUID;
        ReporterName = reporterName;
        ReportedName = reportedName;
        ReporterLocation = reporterLocation;
        ReportedLocation = reportedLocation;
        ReporterServer = reporterServer;
        ReportedServer = reportedServer;
        ReportedGamemode = reportedGamemode;
        ReporterGamemode = reporterGamemode;
        Process = process;
        ReporterIP = reporterIP;
        ReportedIP = reportedIP;
        ReportedHealth = reportedHealth;
        ReporterHealth = reporterHealth;
        this.reportID = reportID;
        this.dateTime = dateTime;
    }

    public String getReason() {
        return Reason;
    }

    public String getDate() {
        return Date;
    }

    public String getReporterUUID() {
        return ReporterUUID;
    }

    public String getReportedUUID() {
        return ReportedUUID;
    }

    public String getReporterName() {
        return ReporterName;
    }

    public String getReportedName() {
        return ReportedName;
    }

    public String getReporterLocation() {
        return ReporterLocation;
    }

    public String getReportedLocation() {
        return ReportedLocation;
    }

    public String getReporterServer() {
        return ReporterServer;
    }

    public String getReportedServer() {
        return ReportedServer;
    }

    public String getReportedGamemode() {
        return ReportedGamemode;
    }

    public String getReporterGamemode() {
        return ReporterGamemode;
    }

    public String getProcess() {
        return Process;
    }

    public String getReporterIP() {
        return ReporterIP;
    }

    public String getReportedIP() {
        return ReportedIP;
    }

    public int getReportedHealth() {
        return ReportedHealth;
    }

    public int getReporterHealth() {
        return ReporterHealth;
    }

    public int getReportID() {
        return reportID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
