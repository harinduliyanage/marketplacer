package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidDataException;

public class StoreStatusInvalidException extends InvalidDataException {
    private static final String code = "STORE-400";

    public StoreStatusInvalidException(String message) {
        super(code, message);
    }
}