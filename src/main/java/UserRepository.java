import org.apache.derby.jdbc.ClientDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {

    private String host;
    private String username;
    private String password;
    private String db;
    private DataSource ds;

    public UserRepository(String host, String username, String password, String db) {
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

    public void resetTable(Connection conn) throws SQLException {
        dropTable(conn);
        createTable(conn);
    }

    public void dropTable(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DROP TABLE USER");
    }

    public void createTable(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("CREATE TABLE USER (" +
                "userName VARCHAR(15) NOT NULL," +
                "password VARCHAR(20)" +
                "PRIMARY KEY (usernName)" +
                ")");
    }

    public void save(Object obj) {

    }

    public void display() {

    }
    public void login(String username, String password) {
        saveLogin(username, password);
        hackLogin(username, password);
    }

    private void hackLogin(String username, String password) {

    }

    private void saveLogin(String username, String password) {

    }
}
