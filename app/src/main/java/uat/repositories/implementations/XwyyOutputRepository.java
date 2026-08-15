package uat.repositories.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import uat.models.XwyyOutput;
import uat.repositories.interfaces.IQueryable;
import uat.repositories.interfaces.IXwyyOutputRepository;
import uat.response.SqlResponse;

@Service
public class XwyyOutputRepository implements IXwyyOutputRepository {

    @Override
    public List<XwyyOutput> getAllRecords() throws Exception {
        // TODO Auto-generated method stub
        try (IQueryable queryable = new Queryable()) {
            List<XwyyOutput> records = new ArrayList<>();
            String sql = "{call MESUAT.SP_XWYYOUTPUT_GETALLOUTPUT(?)}";

            queryable.executeProcedureCursor(sql, rs -> {
                while (rs.next()) {
                    XwyyOutput record = new XwyyOutput();
                    record.setId(rs.getBigDecimal("Id"));
                    record.setLineNo(rs.getString("LineNo"));
                    record.setRecipeName(rs.getString("RecipeName"));
                    record.setRecipeVersion(rs.getString("RecipeVersion"));
                    record.setQty(rs.getBigDecimal("Qty"));
                    record.setProductTime(rs.getString("ProductTime"));
                    record.setIsRead(rs.getBigDecimal("IsRead"));
                    record.setReadTime(rs.getString("ReadTime"));
                    record.setOrderNo(rs.getString("OrderNo"));
                    record.setSublotNo(rs.getString("SublotNo"));
                    records.add(record);
                }
                return null;
            });
            
            return records;
        }catch (Exception e) {
            throw new Exception("Error retrieving all records from XwyyOutput table", e);
        }
    }

    @Override
    public XwyyOutput getRecordById(int id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecordById'");
    }

    @Override
    public SqlResponse createRecord(XwyyOutput model) throws Exception {
        // TODO Auto-generated method stub
        try(IQueryable queryable = new Queryable()) {
            String sql = "{call MESUAT.SP_XWYYOUTPUT_CREATEOUTPUT(?,?,?,?,?,?,?)}";
            SqlResponse response = new SqlResponse();
            queryable.executeProcedureCursor(sql, rs -> {
                if (rs.next()) {
                    response.setStatus(rs.getString("Status"));
                    response.setMessage(rs.getString("Message"));
                }
                return null;
            }, 
            model.getLineNo(), model.getRecipeName(), model.getRecipeVersion(), model.getQty(), model.getOrderNo(), model.getSublotNo());
            return response;
        } catch (Exception e) {
            throw new Exception("Error creating record in XwyyOutput table", e);
        }
    }

    @Override
    public SqlResponse updateRecord(XwyyOutput model, int id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRecord'");
    }

    @Override
    public SqlResponse deleteRecord(int id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecord'");
    }

}
