# 👁 20-20-20 EyeGuard

**20-20-20 EyeGuard** is a Java-based desktop application that helps users follow the 20-20-20 eye care rule: every 20 minutes, look at something 20 feet away for 20 seconds. The app reminds users, logs their compliance, and tracks streaks over time.

---

## Features

- **Signup & Login:** Secure user registration and authentication.
- **Reminders:** Pop-up reminders every 20 minutes to rest your eyes.
- **Logging:** Logs each reminder as "Done" or "Missed".
- **Compliance Tracking:** Displays daily compliance percentage.
- **Streaks:** Tracks consecutive days with ≥80% compliance.

---

## Technology Stack

- **Language:** Java
- **Database:** MySQL
- **GUI:** Swing (`JOptionPane`)
- **JDBC:** Database connectivity

---

## Database Schema

Users
----------------------------
user_id        INT PK AI
username       VARCHAR(50) NOT NULL
email          VARCHAR(100) NOT NULL UNIQUE
password_hash  VARCHAR(255) NOT NULL

EyeGuardLogs
----------------------------
log_id         INT PK AI
user_id        INT NOT NULL FK -> Users(user_id)
log_date       DATE NOT NULL
reminder_time  TIME NOT NULL
status         ENUM('Done','Missed') NOT NULL
UNIQUE(user_id, log_date, reminder_time)
