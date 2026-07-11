package java_courses.repo;

import java.sql.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import java_courses.util.JdbcUtility;
import oracle.jdbc.OracleTypes;

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
     * 
     * @see java_courses.repo.IQueryable#query(java.lang.String, java.lang.Class)
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
     * 
     * @see java_courses.repo.IQueryable#query(java.lang.String, java.lang.Class, int)
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
     * 
     * @see java_courses.repo.IQueryable#query(java.lang.String, java.lang.Class, java.lang.Object[])
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
     * 
     * @see java_courses.repo.IQueryable#executeQuery(java.lang.String, java_courses.repo.ResultSetHandler, java.lang.Object[])
     */
    @Override
    public <T> T executeQuery(
            String sql,
            ResultSetHandler<T> handler,
            Object... params) throws Exception {

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
     * 
     * @see java_courses.repo.IQueryable#executeUpdate(java.lang.String, java.lang.Object[])
     */
    @Override
    public int executeUpdate(
            String sql,
            Object... params) throws Exception {

        try (PreparedStatement stmt =
                     getConnection().prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeUpdate();
        }
    }

    /**    (non-Javadoc)
     * 
     * @see java_courses.repo.IQueryable#executeUpdate(java.lang.String, java.lang.Class)
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
     * 
     * @see java_courses.repo.IQueryable#executeProcedure(java.lang.String, java.lang.Object[])
     */
    @Override
    public int executeProcedure(
            String sql,
            Object... params) throws Exception {

        try (CallableStatement stmt =
                     getConnection().prepareCall(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            stmt.execute();

            return 1;
        }
    }
    /**    (non-Javadoc)
     * 
     * @see java_courses.repo.IQueryable#executeProcedureCursor(java.lang.String, java_courses.repo.ResultSetHandler, java.lang.Object[])
     */
    @Override
    public <T> T executeProcedureCursor(
            String sql,
            ResultSetHandler<T> handler,
            Object... params) throws Exception {

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
     * 
     * @see java_courses.repo.IQueryable#executeScalar(java.lang.String, java.lang.Class, java.lang.Object[])
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T executeScalar(
            String sql,
            Class<T> type) throws Exception {

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
     * 
     * @see java_courses.repo.IQueryable#executeScalar(java.lang.String, java.lang.Class, java.lang.Object[])
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T executeScalar(
            String sql,
            Class<T> type,
            Object... params) throws Exception {

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
     * 
     * @see java_courses.repo.IQueryable#executeBatch(java.lang.String, java.lang.Object[][])
     */
    @Override
    public int[] executeBatch(
            String sql,
            Object[][] batchParams) throws Exception {

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
     * 
     * @see java_courses.repo.IQueryable#beginTransaction()
     */
    @Override
    public void beginTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    /**    (non-Javadoc)
     * 
     * @see java_courses.repo.IQueryable#commit()
     */
    @Override
    public void commit() throws SQLException {

        getConnection().commit();

        getConnection().setAutoCommit(true);
    }

    /**    (non-Javadoc)
     * 
     * @see java_courses.repo.IQueryable#rollback()
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
     * 
     * @see java_courses.repo.IQueryable#close()
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