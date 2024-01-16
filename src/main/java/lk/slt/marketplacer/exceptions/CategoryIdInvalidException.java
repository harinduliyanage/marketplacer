package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidDataException;

public class CategoryIdInvalidException extends InvalidDataException {
    private static final String code = "CATEGORY-400";

    public CategoryIdInvalidException(String message) {
        super(code, message);
    }
}