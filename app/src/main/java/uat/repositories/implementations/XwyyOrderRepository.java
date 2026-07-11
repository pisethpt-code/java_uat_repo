package uat.repositories.implementations;

import org.springframework.stereotype.Service;
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
    public void getAllRecords() throws Exception {
        // TODO Auto-generated method stub
        try (IQueryable queryable = new Queryable()) {
            String sql = "{call MESUAT.SP_XWYYORDER_GETALLRECORDS(?)}";
            queryable.executeProcedureCursor(sql, rs -> {
                while (rs.next()) {
                    System.out.println("Id: " + rs.getInt("Id"));
                    System.out.println("LineNo: " + rs.getString("LineNo"));
                    System.out.println("RecipeName: " + rs.getString("RecipeName"));
                    System.out.println("----------------");
                }
                return null;
            });
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
    public void getRecordById(int id) throws Exception {
        // TODO Auto-generated method stub
        try (IQueryable queryable = new Queryable()) {
            String sql = "{call MESUAT.SP_XWYYORDER_GETRECORDBYID(?, ?)}";
            queryable.executeProcedureCursor(sql, rs -> {
                if (rs.next()) {
                    System.out.println("Id: " + rs.getInt("Id"));
                    System.out.println("LineNo: " + rs.getString("LineNo"));
                    System.out.println("RecipeName: " + rs.getString("RecipeName"));
                }
                return null;
            }, id);
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
                    model.getMSetCount(),
                    model.getMLotNo(),
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
            String sql = "{call MESUAT.SP_XWYYORDER_UpdateRecord(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            int result = queryable.executeProcedure(
                    sql,
                    model.getLineNo(),
                    model.getRecipeName(),
                    model.getRecipeType(),
                    model.getRecipeVersion(),
                    model.getMSetCount(),
                    model.getMLotNo(),
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
     * <br><br>
     * @see uat.repositories.interfaces.IXwyyOrderRepository#deleteRecord(int)
     */
    @Override
    public int deleteRecord(int id) throws Exception {
        // TODO Auto-generated method stub
        try (IQueryable queryable = new Queryable()) {
            String sql = "{call MESUAT.SP_XWYYORDER_DeleteRecord(?)}";
            int result = queryable.executeProcedure(sql, id);
            return result > 0 ? result : -1; // Return the result if successful, otherwise return -1
        } catch (Exception e) {
            throw new Exception("Error deleting record from XwyyOrder table", e);
        }
    }
}
