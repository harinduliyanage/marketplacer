package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidException;

public class OrderNullAttributeException extends InvalidException {
    private static final String code = "ORDER-400";

    public OrderNullAttributeException(String message) {
        super(code, message);
    }
}