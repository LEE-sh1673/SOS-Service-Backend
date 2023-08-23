package group.ict.sosservice.common.supports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("test")
@Component
public class DataCleaner implements InitializingBean {

    private static final String TRUNCATE_QUERY = "SELECT Concat('TRUNCATE TABLE ', TABLE_NAME, ' RESTART IDENTITY;') "
        + "AS q FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA()";

    private static final String TRUNCATE_FORMAT = "TRUNCATE TABLE %s";
    public static final String REFERENTIAL_FORMAT = "SET REFERENTIAL_INTEGRITY %s";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    private List<String> tableNames;

    @Transactional
    public void clear() {
        entityManager.clear();
        truncate();
    }

    private void truncate() {
        entityManager.createNativeQuery(String.format(REFERENTIAL_FORMAT, "FALSE")).executeUpdate();
        for (String tableName : tableNames) {
            entityManager.createNativeQuery(String.format(TRUNCATE_FORMAT, tableName)).executeUpdate();
        }
        entityManager.createNativeQuery(String.format(REFERENTIAL_FORMAT, "TRUE")).executeUpdate();
    }

    @Override
    public void afterPropertiesSet() {
        try (final Connection connection = dataSource.getConnection()) {
            mapTableNames(connection);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private void mapTableNames(final Connection connection)
        throws SQLException {

        final ResultSet tables =  truncateTables(connection);
        tableNames = new ArrayList<>();

        while (tables.next()) {
            tableNames.add(tables.getString("TABLE_NAME"));
        }
    }

    private static ResultSet truncateTables(final Connection connection)
        throws SQLException {
        return connection
            .createStatement()
            .executeQuery(TRUNCATE_QUERY);
    }
}