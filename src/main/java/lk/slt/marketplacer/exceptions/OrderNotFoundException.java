package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class OrderNotFoundException extends RecordNotFoundException {
    private static final String code = "ORDER-404";

    public OrderNotFoundException(String message) {
        super(code, message);
    }
}