package uat.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import uat.filters.XwyyOrderFilter;
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
            String errorMessage = "Error retrieving all records from XwyyOrder table (method: getAllRecords) ";
            response = new Response(false, errorMessage + e.getMessage(), null);
            throw new Exception(errorMessage, e);
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
            if (id <= 0) {
                response = new Response(false, "Invalid record ID.", null);
                return response;
            }
            XwyyOrder data = new XwyyOrder();
            data = repository.getRecordById(id);
            response = new Response(true, "Commit transaction successfully.", data != null ? data : null);
        } catch (Exception e) {
            String errorMessage = "Error retrieving record from XwyyOrder table (method: getRecordById) ID: " + id;
            response = new Response(false, errorMessage + ": " + e.getMessage(), null);
            throw new Exception(errorMessage, e);
        }
        return response;
    }

    /**
     * <br><br>
     * <b>Description: </b> This method is used to create a new record in
     * the XwyyOrder table based on the provided model data.
     * <br><br>
     * @param model
     * @return
     * @throws Exception
     */
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
            String errorMessage = "Error creating record in XwyyOrder table (method: createRecord) ";
            response = new Response(false, errorMessage + e.getMessage(), null);
            throw new Exception(errorMessage, e);
        }
        return response;
    }

    /**
     * <br><br>
     * <b>Description: </b> This method is used to upddate an existing record in the XwyyOrder table based on the provided model data and ID.
     * <br><br>
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @PutMapping(
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
            String errorMessage = "Error updating record in XwyyOrder table (method: updateRecord) ID: " + id;
            response = new Response(false, errorMessage + e.getMessage(), null);
            throw new Exception(errorMessage, e);
        }
        return response;
    }

    /**
     * <br><br>
     * <b>Description: </b> This method is used to delete a specific record from the XwyyOrder table based on the provided ID.
     * <br><br>
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping(
        value ="/deleteRecord",
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public Response deleteRecord(@RequestParam int id) throws Exception {
        Response response = new Response();
        try {
           if (id <= 0) {
                response = new Response(false, "Invalid record ID.", null);
                return response;
            }
            int result = repository.deleteRecord(id);
            response = new Response(true, "Commit transaction successfully.", result);
        } catch (Exception e) {
            String errorMessage = "Error deleting record from XwyyOrder table (method: deleteRecord) ID: " + id;
            response = new Response(false, errorMessage + e.getMessage(), null);
            // throw new Exception(errorMessage, e);
        } 
        return response;
    }

    /**
     * <br><br>
     * <b>Description: </b> This method is used to retrieve records from the XwyyOrder table based on the provided filter criteria.
     * <br><br>
     * @param filter The filter criteria for retrieving records.
     * @return A Response object containing the filtered records or an error message.
     * @throws Exception If an error occurs during the retrieval process.
     */
    @PostMapping(
        value = "/getRecordsByFilter",
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public Response getRecordsByFilter(@RequestBody XwyyOrderFilter filter) throws Exception {
        Response response = new Response();
        try {
            List<XwyyOrder> data = repository.getRecordsByFilter(filter);
            response = new Response(true, "Commit transaction successfully.", data);
        } catch (Exception e) {
            String errorMessage = "Error retrieving records from XwyyOrder table (method: getRecordsByFilter) ";
            response = new Response(false, errorMessage + e.getMessage(), null);
            throw new Exception(errorMessage, e);
        }
        return response;
    }
}
