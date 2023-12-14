package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidDataException;

public class CategoryTypeInvalidException extends InvalidDataException {
    private static final String code = "CATEGORY-400";

    public CategoryTypeInvalidException(String message) {
        super(code, message);
    }
}