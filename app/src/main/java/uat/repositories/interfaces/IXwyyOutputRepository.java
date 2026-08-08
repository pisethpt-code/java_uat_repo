package uat.repositories.interfaces;

import java.util.List;

import uat.models.XwyyOutput;
import uat.response.SqlResponse;

public interface IXwyyOutputRepository {
    public List<XwyyOutput> getAllRecords() throws Exception;
    public XwyyOutput getRecordById(int id) throws Exception;
    public SqlResponse createRecord(XwyyOutput model) throws Exception;
    public SqlResponse updateRecord(XwyyOutput model, int id) throws Exception;
    public SqlResponse deleteRecord(int id) throws Exception;
}
