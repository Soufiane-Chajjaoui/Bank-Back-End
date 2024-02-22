package ma.bank.end.BankBackEnd.Errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class ApiError {
    private int status;
    private String message;
    private Boolean success = false;
    private List<String> details;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "DD-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
    private String debugMessage;


}
