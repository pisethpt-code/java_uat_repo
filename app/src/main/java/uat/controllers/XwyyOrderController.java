package uat.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uat.models.XwyyOrder;
import uat.repositories.interfaces.IXwyyOrderRepository;
import uat.response.Response;

@RestController
@RequestMapping("api/xwyyorder")
public class XwyyOrderController {

    private final IXwyyOrderRepository repository;
    public XwyyOrderController(IXwyyOrderRepository repository) {
        // Constructor with dependency injection
        this.repository = repository;
    }

    /**
     * <br><br>
     * <b>Description: </b> This method is used to retrieve all records from the XwyyOrder table.
     * <br><br>
     * @throws Exception
     */
     @GetMapping(
        value = "/getAllRecords",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public Response getAllRecords() throws Exception {
        Response response = new Response();
        try {
            List<XwyyOrder> data = repository.getAllRecords();
            response = new Response(true, "Commit transaction successfully.", data);
        }catch (Exception e) {
            response = new Response(false, "Error retrieving all records from XwyyOrder table: " + e.getMessage(), null);
            throw new Exception("Error retrieving all records from XwyyOrder table (method: getAllRecords)", e);
        }
        return response;
    }

    /**
     * <br><br>
     * <b>Description: </b> This method is used to retrieve a specific record from the XwyyOrder table based on the provided ID.
     * <br><br>
     * @param id The ID of the record to retrieve.
     * @throws Exception
     */
     @GetMapping(
        value = "/getRecordById",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public Response getRecordById(@RequestParam int id) throws Exception {
        Response response = new Response();
        try {
            XwyyOrder data = new XwyyOrder();
            data = repository.getRecordById(id);
            response = new Response(true, "Commit transaction successfully.", data);
        } catch (Exception e) {
            response = new Response(false, "Error retrieving record from XwyyOrder table: " + e.getMessage(), null);
            throw new Exception("Error retrieving record from XwyyOrder table (method: getRecordById, ID: " + id + ")", e);
        }
        return response;
    }

    @PostMapping(
        value = "/createRecord",
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public Response createRecord(@RequestBody XwyyOrder model) throws Exception {
        Response response = new Response();
        try {
            if (model == null) {
                response = new Response(false, "Invalid model data.", null);
                return response;
            }
            int result = repository.createRecord(model);
            response = new Response(true, "Commit transaction successfully.", result);
        } catch (Exception e) {
            response = new Response(false, "Error creating record in XwyyOrder table: " + e.getMessage(), null);
            throw new Exception("Error creating record in XwyyOrder table (method: createRecord)", e);
        }
        return response;
    }

    @PostMapping(
        value = "/updateRecord",
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public Response updateRecord(@RequestBody XwyyOrder model, @RequestParam int id) throws Exception {
        Response response = new Response();
        try {
            if (model == null) {
                response = new Response(false, "Invalid model data.", null);
                return response;
            }
            int result = repository.updateRecord(model, id);
            response = new Response(true, "Commit transaction successfully.", result);
        } catch (Exception e) {
            response = new Response(false, "Error updating record in XwyyOrder table: " + e.getMessage(), null);
            throw new Exception("Error updating record in XwyyOrder table (method: updateRecord)", e);
        }
        return response;
    }
}
