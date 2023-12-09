package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidDataException;

public class OrderNullAttributeException extends InvalidDataException {
    private static final String code = "ORDER-400";

    public OrderNullAttributeException(String message) {
        super(code, message);
    }
}