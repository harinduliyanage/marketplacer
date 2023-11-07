package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.DuplicateRecordException;

public class UserAlreadyExistsException extends DuplicateRecordException {
    private static final String code = "USER-400";

    public UserAlreadyExistsException(String message) {
        super(code, message);
    }
}
