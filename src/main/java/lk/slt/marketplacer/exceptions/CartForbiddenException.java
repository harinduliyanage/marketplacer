package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.ForbiddenException;

public class CartForbiddenException extends ForbiddenException {
    private static final String code = "Cart-403";

    public CartForbiddenException(String message) {
        super(code, message);
    }
}
