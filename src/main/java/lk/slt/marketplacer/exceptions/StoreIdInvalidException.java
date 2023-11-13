package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidException;

public class StoreIdInvalidException extends InvalidException {
    private static final String code = "STORE-400";

    public StoreIdInvalidException(String message) {
        super(code, message);
    }
}