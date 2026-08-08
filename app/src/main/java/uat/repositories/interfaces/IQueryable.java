package uat.repositories.interfaces;

import java.util.*;

import oracle.jdbc.OracleTypes;
import uat.services.IResultSetHandler;
import java.sql.SQLException;

public interface IQueryable extends AutoCloseable {
    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a query and maps the results to a list of objects of the specified class.
     * <br><br>
     * @see uat.repositories.interfaces.IQueryable#query(java.lang.String, java.lang.Class)
     */
    <T> List<T> query(String sql, Class<T> clazz) throws Exception;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a query with an OUT parameter and maps the results to a list of objects of the specified class.
     * <br><br>
     * @see uat.repositories.interfaces.IQueryable#query(java.lang.String, java.lang.Class, int)
     */
    <T> List<T> query(String sql, Class<T> clazz, int outParameter) throws Exception;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a query with parameters and maps the results to a list of objects of the specified class.
     * <br><br>
     * @see uat.repositories.interfaces.IQueryable#query(java.lang.String, java.lang.Class, java.lang.Object[])
     */
    <T> List<T> query(String sql, Class<T> clazz, Object... params) throws Exception;

    /**    (non-Javadoc)
    * <br><br>
     * <b>Description: </b> Executes a query with parameters and maps the results using a ResultSetHandler.
     * <br><br>
     * @see uat.repositories.interfaces.IQueryable#executeQuery(java.lang.String, uat.services.IResultSetHandler, java.lang.Object[])
     */
    <T> T executeQuery(String sql, IResultSetHandler<T> handler, Object... params) throws Exception;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes an update statement with parameters.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeUpdate(java.lang.String, java.lang.Object[])
     */
    int executeUpdate(String sql, Object... params) throws Exception;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes an update statement using the fields of the specified class as parameters.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeUpdate(java.lang.String, java.lang.Class)
     */
    <T> int executeUpdate(String sql, Class<T> clazz) throws Exception;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a stored procedure with parameters.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeProcedure(java.lang.String, java.lang.Object[])
     */
    int executeProcedure(String sql, Object... params) throws Exception;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a stored procedure that returns a cursor and processes the results using a ResultSetHandler.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeProcedureCursor(java.lang.String, uat.services.IResultSetHandler, java.lang.Object[])
     */
    <T> T executeProcedureCursor(String sql, IResultSetHandler<T> handler, Object... params) throws Exception;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a query that returns a single value and maps it to the specified type.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeScalar(java.lang.String, java.lang.Class, java.lang.Object[])
     */
    <T> T executeScalar(String sql, Class<T> type) throws Exception;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a query that returns a single value with parameters and maps it to the specified type.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeScalar(java.lang.String, java.lang.Class, java.lang.Object[])
     */
    <T> T executeScalar(String sql, Class<T> type, Object... params) throws Exception;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Executes a batch of update statements with parameters.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#executeBatch(java.lang.String, java.lang.Object[][])
     */
    int[] executeBatch(String sql, Object[][] batchParams) throws Exception;

    // create a method to create SQLXML object
    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Creates a SQLXML object for use in executing SQL statements.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#createSQLXML()
     */
    java.sql.SQLXML createSQLXML() throws SQLException;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Begins a transaction by setting auto-commit to false.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#beginTransaction()
     */
    void beginTransaction() throws SQLException;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Commits the current transaction and sets auto-commit back to true.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#commit()
     */
    void commit() throws SQLException;

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Rolls back the current transaction and sets auto-commit back to true.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#rollback()
     */
    void rollback();

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> Closes the database connection.
     * <br><br> 
     * @see uat.repositories.interfaces.IQueryable#close()
     */
    @Override
    void close();
}