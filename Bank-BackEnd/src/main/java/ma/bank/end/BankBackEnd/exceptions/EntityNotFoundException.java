package ma.bank.end.BankBackEnd.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String messageExcepttion) {
        super(messageExcepttion);
    }
}
