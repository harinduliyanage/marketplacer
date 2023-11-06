package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.DuplicateRecordException;

public class CategoryAlreadyExistsException extends DuplicateRecordException {
    private static final String code = "Category-400";
    public CategoryAlreadyExistsException(String message) {
        super(code, message);
    }
}
