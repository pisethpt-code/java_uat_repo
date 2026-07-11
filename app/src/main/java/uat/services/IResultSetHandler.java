package uat.services;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetHandler<T> {
    T handle(ResultSet rSet) throws SQLException;
}
