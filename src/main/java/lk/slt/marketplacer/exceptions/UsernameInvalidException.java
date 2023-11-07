package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidException;

public class UsernameInvalidException extends InvalidException {
    private static final String code = "USER-400";

    public UsernameInvalidException(String message) {
        super(code, message);
    }
}
