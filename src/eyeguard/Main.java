package eyeguard;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to 20-20-20 EyeGuard!");

        while (true) {
            System.out.println("\n1. Signup\n2. Login\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();

                User.signup(username, email, password);

            } else if (choice == 2) {
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();

                User user = User.login(email, password);
                if (user != null) {
                    System.out.println("Login successful! Welcome, " + user.getUsername());

                    EyeGuardTimer.startTimer(user.getUserId());

                    boolean running = true;
                    while (running) {
                        System.out.println("\n1. View today's logs\n2. View streak\n3. Exit");
                        int action = sc.nextInt();
                        sc.nextLine();

                        switch (action) {
                            case 1 -> EyeGuardLog.displayTodaysLogs(user.getUserId());
                            case 2 -> EyeGuardLog.displayStreak(user.getUserId());
                            case 3 -> running = false;
                        }
                    }

                } else {
                    System.out.println("Login failed!");
                }

            } else if (choice == 3) {
                System.out.println("Exiting... Goodbye!");
                break;
            }
        }

        sc.close();
    }
}
