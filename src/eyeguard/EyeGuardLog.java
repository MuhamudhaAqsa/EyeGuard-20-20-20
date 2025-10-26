package eyeguard;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class EyeGuardLog {

    // Log a reminder
    public static void logReminder(int userId, LocalTime reminderTime, boolean done) {
        String sql = "INSERT INTO EyeGuardLogs(user_id, log_date, reminder_time, status) VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE status = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setTime(3, Time.valueOf(reminderTime));
            ps.setString(4, done ? "Done" : "Missed");
            ps.setString(5, done ? "Done" : "Missed");
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Logging reminder failed: " + e.getMessage());
        }
    }

    // Display today's logs
    public static void displayTodaysLogs(int userId) {
        String sql = "SELECT * FROM EyeGuardLogs WHERE user_id = ? AND log_date = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();

            int total = 0;
            int doneCount = 0;

            System.out.println("\nToday's 20-20-20 Logs:");
            while (rs.next()) {
                total++;
                String status = rs.getString("status");
                System.out.println(rs.getTime("reminder_time") + " - " + status);
                if (status.equals("Done")) doneCount++;
            }

            if (total > 0) {
                double compliance = (doneCount * 100.0) / total;
                System.out.printf("Compliance: %.2f%%\n", compliance);
            } else {
                System.out.println("No reminders logged yet today.");
            }

        } catch (SQLException e) {
            System.out.println("Displaying logs failed: " + e.getMessage());
        }
    }

    // Calculate streak: consecutive days with ≥80% compliance
    public static void displayStreak(int userId) {
        String sql = "SELECT log_date, " +
                "SUM(CASE WHEN status='Done' THEN 1 ELSE 0 END)/COUNT(*)*100 AS compliance " +
                "FROM EyeGuardLogs WHERE user_id=? " +
                "GROUP BY log_date ORDER BY log_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            int streak = 0;
            while (rs.next()) {
                double dayCompliance = rs.getDouble("compliance");
                if (dayCompliance >= 80) streak++;
                else break; // streak ends
            }

            System.out.println("Current streak (days with ≥80% compliance): " + streak);

        } catch (SQLException e) {
            System.out.println("Calculating streak failed: " + e.getMessage());
        }
    }
}
