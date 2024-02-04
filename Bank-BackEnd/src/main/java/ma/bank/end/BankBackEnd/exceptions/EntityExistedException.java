package ma.bank.end.BankBackEnd.exceptions;

public class EntityExistedException extends RuntimeException{

    public EntityExistedException() {
    }

    public EntityExistedException(String message) {
        super(message);
    }
}
