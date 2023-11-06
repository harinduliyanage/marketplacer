package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.DuplicateRecordException;

public class StoreAlreadyExistsException extends DuplicateRecordException {
    private static final String code = "Store-400";
    public StoreAlreadyExistsException(String message) {
        super(code, message);
    }
}
