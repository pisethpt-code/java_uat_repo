package uat.controllers;

import org.springframework.stereotype.Controller;
import uat.repositories.interfaces.IXwyyOrderRepository;

@Controller
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
    public void getAllRecords() throws Exception {
        try {
            repository.getAllRecords();
        }catch (Exception e) {
            throw new Exception("Error retrieving all records from XwyyOrder table", e);
        }
    }
}
