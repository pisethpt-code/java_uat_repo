package uat.repositories.interfaces;

import org.springframework.stereotype.Repository;
import uat.models.XwyyOrder;

@Repository
public interface IXwyyOrderRepository {
    public void getAllRecords() throws Exception;
    public void getRecordById(int id) throws Exception;
    public int createRecord(XwyyOrder model) throws Exception;
    public int updateRecord(XwyyOrder model, int id) throws Exception;
    public int deleteRecord(int id) throws Exception;
}
