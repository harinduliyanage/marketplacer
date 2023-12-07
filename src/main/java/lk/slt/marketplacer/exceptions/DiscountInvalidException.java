package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidException;

public class DiscountInvalidException extends InvalidException {
    private static final String code = "ORDER-400";

    public DiscountInvalidException(String message) {
        super(code, message);
    }
}