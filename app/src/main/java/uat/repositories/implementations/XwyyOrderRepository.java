package uat.repositories.implementations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import oracle.jdbc.OracleTypes;
import uat.repositories.interfaces.IQueryable;
import uat.models.XwyyOrder;
import uat.repositories.interfaces.IXwyyOrderRepository;

@Service
public class XwyyOrderRepository implements IXwyyOrderRepository {

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> This method is used to retrieve all records from the XwyyOrder table.
     * <br><br>
     * It establishes a connection to the database, prepares a callable statement to execute the stored procedure
     * @see uat.repositories.interfaces.IXwyyOrderRepository#getAllRecords()
     */
    @Override
    public List<XwyyOrder> getAllRecords() throws Exception {
        // TODO Auto-generated method stub
        try (IQueryable queryable = new Queryable()) {
            List<XwyyOrder> records = new ArrayList<>();
            
            String sql = "{call MESUAT.SP_XWYYORDER_GETALLRECORDS(?)}";
             queryable.executeProcedureCursor(sql, rs -> {
                while (rs.next()) {
                    XwyyOrder order = new XwyyOrder();
                    order.setId(new BigDecimal(rs.getInt("Id")));
                    order.setLineNo(rs.getString("LineNo"));
                    order.setRecipeName(rs.getString("RecipeName"));
                    order.setRecipeType(rs.getString("RecipeType"));
                    order.setRecipeVersion(rs.getString("RecipeVersion"));
                    order.setmSetCount(rs.getBigDecimal("MSetCount"));
                    order.setmLotNo(rs.getString("MLotNo"));
                    order.setChangeTime(rs.getString("ChangeTime"));
                    order.setIsRead(rs.getBigDecimal("IsRead"));
                    order.setMesOrder(rs.getString("MesOrder"));
                    order.setSimpleCode(rs.getString("SimpleCode"));
                    records.add(order);
                }
                return null;
            });

            return records;
        } catch (Exception e) {
            throw new Exception("Error retrieving all records from XwyyOrder table", e);
        }
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> This method is used to retrieve a specific record from
     * the XwyyOrder table based on the provided ID.
     * <br><br>
     * @see uat.repositories.interfaces.IXwyyOrderRepository#getRecordById(int)
     */
    @Override
    public XwyyOrder getRecordById(int id) throws Exception {
        // TODO Auto-generated method stub
        try (IQueryable queryable = new Queryable()) {
            XwyyOrder order = new XwyyOrder();
            String sql = "{call MESUAT.SP_XWYYORDER_GETRECORDBYID(?, ?)}";
            queryable.executeProcedureCursor(sql, rs -> {
                if (rs.next()) {
                    order.setId(new BigDecimal(rs.getInt("Id")));
                    order.setLineNo(rs.getString("LineNo"));
                    order.setRecipeName(rs.getString("RecipeName"));
                    order.setRecipeType(rs.getString("RecipeType"));
                    order.setRecipeVersion(rs.getString("RecipeVersion"));
                    order.setmSetCount(rs.getBigDecimal("MSetCount"));
                    order.setmLotNo(rs.getString("MLotNo"));
                    order.setChangeTime(rs.getString("ChangeTime"));
                    order.setIsRead(rs.getBigDecimal("IsRead"));
                    order.setMesOrder(rs.getString("MesOrder"));
                    order.setSimpleCode(rs.getString("SimpleCode"));
                }
                return null;
            }, id);

            return order;
        } catch (Exception e) {
            throw new Exception("Error retrieving record from XwyyOrder table", e);
        }
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> This method is used to create a new record in the XwyyOrder table based on the provided model.
     * <br><br>
     * @see uat.repositories.interfaces.IXwyyOrderRepository#createRecord(uat.models.XwyyOrder)
     */
    @Override
    public int createRecord(XwyyOrder model) throws Exception {
        // TODO Auto-generated method stub
       try(IQueryable queryable = new Queryable()) {
            String sql = "{call MESUAT.SP_XWYYORDER_CreateRecord(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            int result = queryable.executeProcedure(
                    sql,
                    model.getLineNo(),
                    model.getRecipeName(),
                    model.getRecipeType(),
                    model.getRecipeVersion(),
                    model.getmSetCount(),
                    model.getmLotNo(),
                    model.getIsRead(),
                    model.getMesOrder(),
                    model.getSimpleCode());

            return result > 0 ? result : -1; // Return the result if successful, otherwise return -1
        } catch (Exception e) {
            throw new Exception("Error creating record in XwyyOrder table", e);
        }
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> This method is used to update an existing record in
     * the XwyyOrder table based on the provided model and ID.
     * <br><br>
     * @see uat.repositories.interfaces.IXwyyOrderRepository#updateRecord(uat.models.XwyyOrder, int)
     */
    @Override
    public int updateRecord(XwyyOrder model, int id) throws Exception {
        // TODO Auto-generated method stub
        try (IQueryable queryable = new Queryable()) {
            String sql = "{call MESUAT.SP_XWYYORDER_UPDATERECORD(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            int result = queryable.executeProcedure(
                    sql,
                    model.getLineNo(),
                    model.getRecipeName(),
                    model.getRecipeType(),
                    model.getRecipeVersion(),
                    model.getmSetCount(),
                    model.getmLotNo(),
                    model.getIsRead(),
                    model.getMesOrder(),
                    model.getSimpleCode(),
                    id);

            return result > 0 ? result : -1; // Return the result if successful, otherwise return -1
        } catch (Exception e) {
            throw new Exception("Error updating record in XwyyOrder table", e);
        }
    }

    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> This method is used to delete a specific record from the XwyyOrder table based on the provided ID.
     * @param id The ID of the record to be deleted. <i>Note: The ID should be a positive integer.</i>
     * <br><br>
     * @return An integer indicating the result of the delete operation. Returns a positive value if successful, otherwise returns -1.
     * @throws Exception If an error occurs during the delete operation.
     * <br><br>
     * @see uat.repositories.interfaces.IXwyyOrderRepository#deleteRecord(int)
     */
    @Override
    public int deleteRecord(int id) throws Exception {
        // TODO Auto-generated method stub
        try (IQueryable queryable = new Queryable()) {
            String sql = "{call MESUAT.SP_XWYYORDER_DELETERECORD(?, ?)}";
            int result = queryable.executeProcedureCursor(sql, (rs) -> {
                // Handle the result set if needed
                if (rs.next()) {
                    rs.getString("Status"); // Assuming the stored procedure returns a status
                    rs.getString("Message"); // Assuming the stored procedure returns a response message

                    System.out.println(rs.getString("Status"));
                    System.out.println(rs.getString("Message"));
                }
                return 1; // Placeholder return value
            }, id);
            return result > 0 ? result : -1; // Return the result if successful, otherwise return -1
        } catch (Exception e) {
            throw new Exception("Error deleting record from XwyyOrder table", e);
        }
    }
}
