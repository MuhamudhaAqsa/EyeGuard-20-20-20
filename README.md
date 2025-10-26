#  20-20-20 EyeGuard

**20-20-20 EyeGuard** is a Java desktop application that helps users follow the 20-20-20 eye care rule: every 20 minutes, look at something 20 feet away for 20 seconds. The app reminds users, logs compliance, and tracks streaks over time.

---

## Features

- **Signup & Login** – Secure user registration and authentication  
- **20-Minute Reminders** – Pop-up notifications to rest your eyes  
- **Logging** – Records each reminder as "Done" or "Missed"  
- **Daily Compliance** – Shows percentage of reminders completed  
- **Streak Tracking** – Tracks consecutive days with ≥80% compliance  

---

## Technology Stack

- **Language:** Java  
- **GUI:** Swing (`JOptionPane`)  
- **Database:** MySQL  
- **Connectivity:** JDBC  

---

## Project Structure

| File | Description |
|------|-------------|
| `DBConnection.java` | Handles database connection |
| `User.java` | User signup and login logic |
| `EyeGuardLog.java` | Logs reminders, displays compliance & streaks |
| `EyeGuardTimer.java` | 20-minute reminder timer with pop-ups |
| `Main.java` | CLI menu and program entry point |

---

## Database Schema

**Users Table**

| Column         | Type         | Key          |
|----------------|-------------|--------------|
| user_id        | INT         | PK, AI       |
| username       | VARCHAR(50) | NOT NULL     |
| email          | VARCHAR(100)| NOT NULL, UNIQUE |
| password  | VARCHAR(255)| NOT NULL     |

**EyeGuardLogs Table**

| Column        | Type             | Key          |
|---------------|-----------------|--------------|
| log_id        | INT             | PK, AI       |
| user_id       | INT             | FK → Users   |
| log_date      | DATE            | NOT NULL     |
| reminder_time | TIME            | NOT NULL     |
| status        | ENUM('Done','Missed') | NOT NULL |
| UNIQUE(user_id, log_date, reminder_time) |   | |

---

## Usage

1. Signup or login with your credentials.  
2. Timer starts; reminders appear every 20 minutes.  
3. Click **Done** or **Missed** for each reminder.  
4. View today’s logs and streaks from the menu.  

---

## Notes

- Uses `java.util.Timer` for scheduling reminders.  
- All database operations use prepared statements for security.  
- Streaks are calculated for consecutive days with ≥80% compliance.  
