package eyeguard;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class EyeGuardTimer {

    public static void startTimer(int userId) {
        Timer timer = new Timer();
        long interval = 20 * 60 * 1000; // 20 minutes in milliseconds

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalTime now = LocalTime.now();

                // Swing popup panel
                SwingUtilities.invokeLater(() -> {
                    int result = JOptionPane.showOptionDialog(
                            null,
                            "🔔 Time to rest your eyes for 20 seconds!",
                            "20-20-20 EyeGuard Reminder",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new String[]{"Done", "Missed"},
                            "Done"
                    );

                    boolean done = result == 0; // 0 = Done button clicked

                    // Log the reminder in database
                    EyeGuardLog.logReminder(userId, now, done);

                    // Optional: Play beep sound
                    Toolkit.getDefaultToolkit().beep();
                });
            }
        }, 0, interval);
    }
}
