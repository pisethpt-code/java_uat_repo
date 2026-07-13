package uat.repositories.implementations;

import java.sql.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import oracle.jdbc.OracleTypes;
import uat.repositories.interfaces.IQueryable;
import uat.services.IResultSetHandler;
import uat.utils.JdbcUtility;

@Service
public class Queryable implements IQueryable {

    private Connection connection;

    public Queryable() throws SQLException {
        this.connection = JdbcUtility.getConnection();
    }

    private Connection getConnection() {
        return connection;
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a query and maps the results to a list of objects of the specified class.
     * <br><br>
     * @see uat.repositories.interfaces.IQueryable#query(java.lang.String, java.lang.Class)
     */
    @Override
    public <T> List<T> query(String sql, Class<T> clazz) throws Exception {

        List<T> list = new ArrayList<>();

        try (
                CallableStatement stmt = getConnection().prepareCall(sql)) {

            // OUT SYS_REFCURSOR
            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {

                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                while (rs.next()) {

                    T obj = clazz.newInstance();

                    for (int i = 1; i <= columnCount; i++) {

                        String columnName = meta.getColumnLabel(i);

                        try {

                            Field field = clazz.getDeclaredField(columnName);

                            field.setAccessible(true);

                            field.set(obj, rs.getObject(i));

                        } catch (NoSuchFieldException ignored) { }
                    }
                    list.add(obj);
                }
            }
        }
        return list;
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a query with an OUT parameter and maps the results to a list of objects of the specified class.
     * <br><br>
     * @see uat.repositories.interfaces.IQueryable#query(java.lang.String, java.lang.Class, int)
     */
    @Override
    public <T> List<T> query(String sql, Class<T> clazz, int outParameter) throws Exception {

        List<T> list = new ArrayList<>();

        try (
                CallableStatement stmt = getConnection().prepareCall(sql)) {

            // OUT SYS_REFCURSOR
            stmt.registerOutParameter(1, outParameter);

            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {

                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                while (rs.next()) {

                    T obj = clazz.newInstance();

                    for (int i = 1; i <= columnCount; i++) {

                        String columnName = meta.getColumnLabel(i);

                        try {

                            Field field = clazz.getDeclaredField(columnName);

                            field.setAccessible(true);

                            field.set(obj, rs.getObject(i));

                        } catch (NoSuchFieldException ignored) {
                        }

                    }

                    list.add(obj);
                }
            }

        }

        return list;
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a query with parameters and maps the results to a list of objects of the specified class.
     * <br><br>
     * @see uat.repositories.interfaces.IQueryable#query(java.lang.String, java.lang.Class, java.lang.Object[])
     */
    @Override
    public <T> List<T> query(String sql, Class<T> clazz, Object... params) throws Exception {

        List<T> list = new ArrayList<>();

        try (
                CallableStatement stmt = getConnection().prepareCall(sql)) {

            // OUT SYS_REFCURSOR
            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            // IN parameters
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 2, params[i]);
            }

            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {

                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                while (rs.next()) {

                    T obj = clazz.newInstance();

                    for (int i = 1; i <= columnCount; i++) {

                        String columnName = meta.getColumnLabel(i);

                        try {

                            Field field = clazz.getDeclaredField(columnName);

                            field.setAccessible(true);

                            field.set(obj, rs.getObject(i));

                        } catch (NoSuchFieldException ignored) {
                        }

                    }

                    list.add(obj);
                }
            }

        }

        return list;
    }

    /**    (non-Javadoc)
    * <br><br>
     * <b>Description: </b> Executes a query with parameters and maps the results using a ResultSetHandler.
     * <br><br>
     * @see uat.repositories.interfaces.IQueryable#executeQuery(java.lang.String, uat.services.IResultSetHandler, java.lang.Object[])
     */
    @Override
    public <T> T executeQuery(String sql, IResultSetHandler<T> handler, Object... params) throws Exception {

        try (PreparedStatement stmt =
                     getConnection().prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                return handler.handle(rs);
            }
        }
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes an update statement with parameters.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeUpdate(java.lang.String, java.lang.Object[])
     */
    @Override
    public int executeUpdate(String sql, Object... params) throws Exception {

        try (PreparedStatement stmt =
                     getConnection().prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeUpdate();
        }
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes an update statement using the fields of the specified class as parameters.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeUpdate(java.lang.String, java.lang.Class)
     */
    @Override
    public <T> int executeUpdate(String sql, Class<T> clazz) throws Exception {

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            Field[] fields = clazz.getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {

                fields[i].setAccessible(true);

                stmt.setObject(i + 1, fields[i].get(clazz));
            }

            return stmt.executeUpdate();
        }
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a stored procedure with parameters.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeProcedure(java.lang.String, java.lang.Object[])
     */
    @Override
    public int executeProcedure(String sql, Object... params) throws Exception {
        try (CallableStatement stmt = getConnection().prepareCall(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            stmt.execute();

            return 1;
        }
    }
    
    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a stored procedure that returns a cursor and processes the results using a ResultSetHandler.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeProcedureCursor(java.lang.String, uat.services.IResultSetHandler, java.lang.Object[])
     */
    @Override
    public <T> T executeProcedureCursor(String sql, IResultSetHandler<T> handler, Object... params) throws Exception {
        try (CallableStatement stmt =
                     getConnection().prepareCall(sql)) {

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 2, params[i]);
            }

            stmt.execute();

            try (ResultSet rs =
                         (ResultSet) stmt.getObject(1)) {

                return handler.handle(rs);
            }
        }
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a query that returns a single value and maps it to the specified type.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeScalar(java.lang.String, java.lang.Class, java.lang.Object[])
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T executeScalar(String sql, Class<T> type) throws Exception {
        try (PreparedStatement stmt =
                     getConnection().prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return (T) rs.getObject(1);
                }

                return null;
            }
        }
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a query that returns a single value with parameters and maps it to the specified type.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeScalar(java.lang.String, java.lang.Class, java.lang.Object[])
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T executeScalar(String sql, Class<T> type, Object... params) throws Exception {
        try (PreparedStatement stmt =
                     getConnection().prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return (T) rs.getObject(1);
                }

                return null;
            }
        }
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a batch of update statements with parameters.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeBatch(java.lang.String, java.lang.Object[][])
     */
    @Override
    public int[] executeBatch(String sql, Object[][] batchParams) throws Exception {
        try (PreparedStatement stmt =
                     getConnection().prepareStatement(sql)) {

            for (Object[] row : batchParams) {

                for (int i = 0; i < row.length; i++) {
                    stmt.setObject(i + 1, row[i]);
                }

                stmt.addBatch();
            }

            return stmt.executeBatch();
        }
    }
   
    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Begins a transaction by setting auto-commit to false.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#beginTransaction()
     */
    @Override
    public void beginTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Commits the current transaction and sets auto-commit back to true.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#commit()
     */
    @Override
    public void commit() throws SQLException {
        getConnection().commit();
        getConnection().setAutoCommit(true);
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Rolls back the current transaction and sets auto-commit back to true.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#rollback()
     */
    @Override
    public void rollback() {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ignored) {
        }
    }
    
    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Closes the database connection.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#close()
     */
    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ignored) {
        }
    }
}