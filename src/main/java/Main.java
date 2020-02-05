import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static UserRepository userRepository = new UserRepository(
            "localhost",
            "app",
            "app",
            "db"
    );

    public static void main(String[] args) {
        try (Connection conn = userRepository.getDs().getConnection()) {
            userRepository.createTable(conn);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
