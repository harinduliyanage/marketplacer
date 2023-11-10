package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidException;

public class CategoryTypeInvalidException extends InvalidException {
    private static final String code = "CATEGORY-400";

    public CategoryTypeInvalidException(String message) {
        super(code, message);
    }
}