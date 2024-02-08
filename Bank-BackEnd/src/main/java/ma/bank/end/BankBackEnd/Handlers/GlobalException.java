package ma.bank.end.BankBackEnd.Handlers;

import ma.bank.end.BankBackEnd.Errors.ApiError;
import ma.bank.end.BankBackEnd.exceptions.EntityExistedException;
import ma.bank.end.BankBackEnd.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleBankAccountNotFoundException(EntityNotFoundException e){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(EntityExistedException.class)
    public ResponseEntity<?> handleEntityExistedException(EntityExistedException e){
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String>> errorNotValidArg(MethodArgumentNotValidException e){
        Map<String, String> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));

        return new ResponseEntity<Map<String, String>>(fieldErrors , HttpStatus.BAD_REQUEST);
    }
}
