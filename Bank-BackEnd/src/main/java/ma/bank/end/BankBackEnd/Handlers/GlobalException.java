package ma.bank.end.BankBackEnd.Handlers;

import ma.bank.end.BankBackEnd.Errors.ApiError;
import ma.bank.end.BankBackEnd.exceptions.EntityExistedException;
import ma.bank.end.BankBackEnd.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
