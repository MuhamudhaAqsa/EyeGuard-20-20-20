package eyeguard;

import java.sql.*;

public class User {
    private int userId;
    private String username;
    private String email;

    // Signup
    public static boolean signup(String username, String email, String passwordHash) {
        String sql = "INSERT INTO Users(username, email, password_hash) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, passwordHash);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("Signup successful! User ID: " + rs.getInt(1));
            }
            return true;

        } catch (SQLException e) {
            System.out.println("Signup failed: " + e.getMessage());
            return false;
        }
    }

    // Login
    public static User login(String email, String passwordHash) {
        String sql = "SELECT * FROM Users WHERE email = ? AND password_hash = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, passwordHash);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.userId = rs.getInt("user_id");
                user.username = rs.getString("username");
                user.email = rs.getString("email");
                return user;
            }

        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return null;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
}
