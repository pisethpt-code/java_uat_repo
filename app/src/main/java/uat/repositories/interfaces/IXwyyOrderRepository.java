package uat.repositories.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import uat.models.XwyyOrder;

@Repository
public interface IXwyyOrderRepository {
    public List<XwyyOrder> getAllRecords() throws Exception;
    public XwyyOrder getRecordById(int id) throws Exception;
    public int createRecord(XwyyOrder model) throws Exception;
    public int updateRecord(XwyyOrder model, int id) throws Exception;
    public int deleteRecord(int id) throws Exception;
}
