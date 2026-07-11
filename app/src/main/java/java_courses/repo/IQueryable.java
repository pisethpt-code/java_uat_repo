package java_courses.repo;
import java.util.*;
import java.sql.SQLException;

public interface IQueryable extends AutoCloseable {

    <T> List<T> query(String sql, Class<T> clazz) throws Exception;

    <T> List<T> query(String sql, Class<T> clazz, int outParameter) throws Exception;

    <T> List<T> query(String sql, Class<T> clazz, Object... params) throws Exception;

    <T> T executeQuery(String sql, ResultSetHandler<T> handler, Object... params) throws Exception;

    int executeUpdate(String sql, Object... params) throws Exception;

    <T> int executeUpdate(String sql, Class<T> clazz) throws Exception;

    int executeProcedure(String sql, Object... params) throws Exception;

    <T> T executeProcedureCursor(String sql, ResultSetHandler<T> handler, Object... params) throws Exception;

    <T> T executeScalar(String sql, Class<T> type) throws Exception;

    <T> T executeScalar(String sql, Class<T> type, Object... params) throws Exception;

    int[] executeBatch(String sql, Object[][] batchParams) throws Exception;

    void beginTransaction() throws SQLException;

    void commit() throws SQLException;

    void rollback();

    @Override
    void close();
}