package ma.bank.end.BankBackEnd.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @AllArgsConstructor @NoArgsConstructor
public class ResponseError {
    private String message;
    private int statusCode;
    private Date timestamp;
}
