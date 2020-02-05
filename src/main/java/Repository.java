import org.apache.derby.jdbc.ClientDataSource;

import javax.sql.DataSource;

public class Repository {

    private String tableName;
    private String host;
    private String username;
    private String password;
    private String db;
    private DataSource ds;

    public Repository(String tableName, String host, String username, String password, String db) {
        this.tableName = tableName;
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

    public void resetTable() {

    }

    public void save(Object obj) {

    }

    public void display() {

    }
}
