package uat.repositories.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import uat.models.XwyyOrder;

@Repository
public interface IXwyyOrderRepository {
    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> This method is used to retrieve all records from the XwyyOrder table.
     * <br><br>
     * It establishes a connection to the database, prepares a callable statement to execute the stored procedure
     * @see uat.repositories.interfaces.IXwyyOrderRepository#getAllRecords()
     */
    public List<XwyyOrder> getAllRecords() throws Exception;
    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> This method is used to retrieve a specific record from
     * the XwyyOrder table based on the provided ID.
     * <br><br>
     * @see uat.repositories.interfaces.IXwyyOrderRepository#getRecordById(int)
     */
    public XwyyOrder getRecordById(int id) throws Exception;
    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> This method is used to create a new record in the XwyyOrder table based on the provided model.
     * <br><br>
     * @see uat.repositories.interfaces.IXwyyOrderRepository#createRecord(uat.models.XwyyOrder)
     */
    public int createRecord(XwyyOrder model) throws Exception;
    /**    (non-Javadoc)
     * <br><br>
     * <b>Description: </b> This method is used to update an existing record in
     * the XwyyOrder table based on the provided model and ID.
     * <br><br>
     * @see uat.repositories.interfaces.IXwyyOrderRepository#updateRecord(uat.models.XwyyOrder, int)
     */
    public int updateRecord(XwyyOrder model, int id) throws Exception;
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
    public int deleteRecord(int id) throws Exception;
}
