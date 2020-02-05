import org.apache.derby.jdbc.ClientDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

public class AccountRepository {

    private String host;
    private String username;
    private String password;
    private String db;
    private DataSource ds;
    private Connection conn;

    public AccountRepository(String host, String username, String password, String db) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.db = db;
        this.ds = getDataSource();
    }

    public DataSource getDataSource() {
        ClientDataSource dataSource = new ClientDataSource();

        dataSource.setServerName(this.host);
        dataSource.setUser(this.username);
        dataSource.setPassword(this.password);
        dataSource.setDatabaseName(this.db);

        return dataSource;
    }

    public DataSource getDs() {
        return ds;
    }

    public void resetTable() throws SQLException {
        dropTable();
        createTable();
    }

    public void dropTable() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DROP TABLE ACCOUNT");
        statement.executeUpdate();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("CREATE TABLE ACCOUNT (" +
                "NAME VARCHAR(15) NOT NULL," +
                "PASSWORD VARCHAR(20)," +
                "PRIMARY KEY (NAME)" +
                ")");
        statement.executeUpdate();
    }

    public void save(Account user) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO ACCOUNT VALUES (?, ?)");
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.execute();
    }

    public void hackLogin(String username, String password) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNT WHERE NAME='"+username+"' AND PASSWORD='" + password + "'");

        System.out.println();

        while (resultSet.next()) {
            System.out.println("Profile");
            System.out.println("=======");

            System.out.println("Name: " + resultSet.getString("NAME"));
            System.out.println("Password: " + resultSet.getString("PASSWORD"));
        }
    }

    public void saveLogin(String username, String password) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM ACCOUNT WHERE NAME=? AND PASSWORD=?");

        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        System.out.println();

        while (resultSet.next()) {
            System.out.println("Profile");
            System.out.println("=======");

            System.out.println("Name: " + resultSet.getString("NAME"));
            System.out.println("Password: " + resultSet.getString("PASSWORD"));
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
