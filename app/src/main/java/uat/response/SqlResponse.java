package uat.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "SqlResponse")
public class SqlResponse {
    private String Status;
    private String Message;
    private int Code;
    private String ExecutedDateTime;

    public SqlResponse() {
        super();
    }

    public SqlResponse(String status, String message) {
        this.Status = status;
        this.Message = message;
        this.ExecutedDateTime = LocalDateTime.now().toString();
    }

    public SqlResponse(String status, String message, int code) {
        this.Status = status;
        this.Message = message;
        this.Code = code;
        this.ExecutedDateTime = LocalDateTime.now().toString();
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
    
    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getExecutedDateTime() {
        return ExecutedDateTime;
    }

    public void setExecutedDateTime(String executedDateTime) {
        ExecutedDateTime = executedDateTime;
    }
}
