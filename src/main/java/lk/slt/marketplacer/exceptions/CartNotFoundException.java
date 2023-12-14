package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class CartNotFoundException extends RecordNotFoundException {
    private static final String code = "CART-404";

    public CartNotFoundException(String message) {
        super(code, message);
    }
}
