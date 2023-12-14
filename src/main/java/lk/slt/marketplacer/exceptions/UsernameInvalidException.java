package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidDataException;

public class UsernameInvalidException extends InvalidDataException {
    private static final String code = "USER-400";

    public UsernameInvalidException(String message) {
        super(code, message);
    }
}
