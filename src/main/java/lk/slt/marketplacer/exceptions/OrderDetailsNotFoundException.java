package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class OrderDetailsNotFoundException extends RecordNotFoundException {
    private static final String code = "ORDER-DETAILS-404";

    public OrderDetailsNotFoundException(String message) {
        super(code, message);
    }
}