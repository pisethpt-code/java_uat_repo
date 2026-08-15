package uat.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uat.response.Response;
import uat.runnables.Schedule;

@RestController
@RequestMapping("api/schedule")
public class ScheduleController {

    @GetMapping
    (
        value = "/startSchedule", 
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public Response startSchedule() {
        // Implement the logic to start the schedule
        try {
            Schedule.startSchedule();
            return new Response(true, "Schedule started successfully", null);
        } catch (Exception e) {
            // TODO: handle exception
            return new Response(false, "Failed to start schedule: " + e.getMessage(), null);
        }
    }

    @GetMapping
    (
        value = "/createXyyOutputSchedule", 
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public Response createXyyOutputSchedule() {
        // Implement the logic to create Xyy output schedule
        try {
            Schedule.createXyyOutputSchedule();
            return new Response(true, "Xyy output schedule created successfully", null);
        } catch (Exception e) {
            return new Response(false, "Failed to create Xyy output schedule: " + e.getMessage(), null);
        }
    }

    @GetMapping
    (
        value = "/stopXyyOutputSchedule", 
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public Response stopXyyOutputSchedule() {
        // Implement the logic to stop the Xyy output schedule
        try {
            Schedule.stopXyyOutputSchedule();
            return new Response(true, "Xyy output schedule stopped successfully", null);
        } catch (Exception e) {
            return new Response(false, "Failed to stop Xyy output schedule: " + e.getMessage(), null);
        }
    }
}
