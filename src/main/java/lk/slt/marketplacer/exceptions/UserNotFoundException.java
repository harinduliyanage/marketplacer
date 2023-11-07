package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class UserNotFoundException extends RecordNotFoundException {

    private static final String code = "USER-404";

    public UserNotFoundException(String message) {
        super(code, message);
    }
}
