package uat.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Response")
/**
 * <br><br>
 * <b>Description: </b> This class represents a response object that encapsulates the result
 * of an operation, including success status, message, executed time, and optional data.
 * <br><br>
 * Response
 */
public class Response {

    private boolean success;
    private String message;
    private String executedTime;
    private Object data;

    public Response() {
    }

    /**
     * <br><br>
     * <b>Description: </b> This constructor is used to create a Response object with
     * the specified success status and message.
     * <br><br>
     * @param success
     * @param message
     */
    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.executedTime = java.time.LocalDateTime.now().toString();
    }

    /**
     * <br><br>
     * <b>Description: </b> This constructor is used to create a Response object with
     * the specified success status, message, and data.
     * <br><br>
     * @param success
     * @param message
     * @param data
     */
    public Response(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.executedTime = java.time.LocalDateTime.now().toString();
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(String executedTime) {
        this.executedTime = executedTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}