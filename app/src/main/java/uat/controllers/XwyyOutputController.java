package uat.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import uat.models.XwyyOutput;
import uat.repositories.interfaces.IXwyyOutputRepository;
import uat.response.Response;
import uat.response.SqlResponse;

@RestController
@RequestMapping("api/xwyyoutput")
public class XwyyOutputController {
    private final IXwyyOutputRepository repository;

    public XwyyOutputController(IXwyyOutputRepository repository) {
        this.repository = repository;
    }

    @GetMapping (
        value = "/getAllOutputs",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public Response getAllOutputs() throws Exception {
        Response response = new Response();
        try {
            List<XwyyOutput> data = repository.getAllRecords();
            response = new Response(true, "Commit transaction successfully.", data);
        } catch (Exception e) {
            String errorMessage = "Error retrieving all records from XwyyOutput table (method: getAllOutput) ";
            response = new Response(false, errorMessage + e.getMessage(), null);
            // throw new Exception(errorMessage, e);
        }
        return response;
    }

    @PostMapping(
        value = "/createOutput",
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public Response createOutput(@RequestBody XwyyOutput model) throws Exception{
        Response response = new Response();
        try {
            if (model == null)
            {
                response = new Response(false, "Invalid input: model is null.", null);
                // throw new Exception("Invalid input: model is null.");
            }
            SqlResponse sqlResponse = repository.createRecord(model);

            if (sqlResponse.getStatus().equalsIgnoreCase("Success")) {
                response = new Response(true, sqlResponse.getMessage(), null);
            } else {
                response = new Response(false, sqlResponse.getMessage(), null);
                // throw new Exception(sqlResponse.getMessage());
            }
        } catch (Exception e) {
            String errorMessage = "Error creating record in XwyyOutput table (method: createOutput) ";
            response = new Response(false, errorMessage + e.getMessage(), null);
            // throw new Exception(errorMessage, e);
        }
        return response;            
    }
}
