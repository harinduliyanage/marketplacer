package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidDataException;

public class CartEmptyException extends InvalidDataException {
    private static final String code = "ORDER-400";
    public CartEmptyException(String message) {
        super(code, message);
    }
}
