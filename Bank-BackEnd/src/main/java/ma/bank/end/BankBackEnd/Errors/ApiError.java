package ma.bank.end.BankBackEnd.Errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class ApiError {
    private int status;
    private String message;
    private Boolean success;
    private List<String> details;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "DD-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String debugMessage;
    public ApiError() {
        timestamp = LocalDateTime.now();
        this.success = Boolean.FALSE;
    }

    public ApiError(HttpStatus status) {
        this();
        this.status =  status.value();
    }

    public ApiError(HttpStatus status, RuntimeException ex) {
        this();
        this.status =  status.value();
        this.message = ex.getMessage();
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, Throwable ex , List<String> details) {
        this();
        this.status = status.value();
        this.message = ex.getMessage();
        this.debugMessage = ex.getLocalizedMessage();
        this.details = details;
    }


}
