package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidDataException;

public class StoreIdInvalidException extends InvalidDataException {
    private static final String code = "STORE-400";

    public StoreIdInvalidException(String message) {
        super(code, message);
    }
}