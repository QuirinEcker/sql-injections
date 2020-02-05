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
            userRepository.save(new Account("user0", "pw0"));
            userRepository.save(new Account("user1", "pw1"));
            userRepository.save(new Account("user2", "pw2"));

            System.out.println("LogIn");
            System.out.print("USERNAME: ");
            String username = scanner.nextLine();
            System.out.print("PASSWORD: ");
            String password = scanner.nextLine();

            userRepository.hackLogin(username, password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
