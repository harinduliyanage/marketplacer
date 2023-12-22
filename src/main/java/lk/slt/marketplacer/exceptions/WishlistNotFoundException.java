package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class WishlistNotFoundException extends RecordNotFoundException {
    private static final String code = "WISHLIST-404";

    public WishlistNotFoundException(String message) {
        super(code, message);
    }
}
