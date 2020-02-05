import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

    private static AccountRepository userRepository = new AccountRepository(
            "localhost",
            "app",
            "app",
            "db"
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = userRepository.getDs().getConnection()) {
            userRepository.setConn(conn);
            userRepository.resetTable();
            userRepository.save(new Account("User0", "pw0"));
            userRepository.save(new Account("User1", "pw1"));
            userRepository.save(new Account("User2", "pw3"));

            System.out.println("LogIn");
            System.out.println("USERNAME: ");
            String username = scanner.next();
            System.out.println("PASSWORD: ");
            String password = scanner.next();

            userRepository.hackLogin(username, password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
