package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.DuplicateRecordException;

public class CartEmtyException extends DuplicateRecordException {
    private static final String code = "ORDER-400";
    public CartEmtyException(String message) {
        super(code, message);
    }
}
