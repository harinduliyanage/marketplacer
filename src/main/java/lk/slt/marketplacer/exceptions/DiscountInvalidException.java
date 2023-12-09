package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidDataException;

public class DiscountInvalidException extends InvalidDataException {
    private static final String code = "ORDER-400";

    public DiscountInvalidException(String message) {
        super(code, message);
    }
}