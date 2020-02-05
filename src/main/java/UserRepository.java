import org.apache.derby.jdbc.ClientDataSource;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {

    private String host;
    private String username;
    private String password;
    private String db;
    private DataSource ds;
    private Connection conn;

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

    public void resetTable() throws SQLException {
        dropTable();
        createTable();
    }

    public void dropTable() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DROP TABLE USER");
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("CREATE TABLE USER (" +
                "userName VARCHAR(15) NOT NULL," +
                "password VARCHAR(20)" +
                "PRIMARY KEY (usernName)" +
                ")");
    }

    public void save(User user) throws SQLException {
    }

    public void saveSave(User user) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO USER VALUES (?, ?)");
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.execute();
    }

    public void hackSave(User user) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("INSERT INTO USER VALUES (" + user.getUsername() + ", " + user.getPassword() + ")");
    }

    public void display() {

    }
    public void login(String username, String password) {

    }

    private void hackLogin(String username, String password) {

    }

    private void saveLogin(String username, String password) {

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
