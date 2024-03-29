package vn.hcmuaf.edu.fit.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;
import vn.hcmuaf.edu.fit.services.BillService;

import java.sql.SQLException;

public class JDBIConnector {
    Jdbi jdbi;
    static JDBIConnector connector;

    public JDBIConnector() {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://"+ DBProperties.host()+":"+DBProperties.port()+"/"+DBProperties.dbName());
            dataSource.setUser(DBProperties.user());
            dataSource.setPassword(DBProperties.pass());
            dataSource.setAutoReconnect(true);
            dataSource.setUseCompression(true);
            jdbi = Jdbi.create(dataSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Jdbi get() {
        if (connector == null) connector = new JDBIConnector();
        return connector.jdbi;
    }

    public static void main(String[] args) {
        System.out.println(BillService.getInstance().getAllBillByUserAndStatus(28, ""));
    }

}

