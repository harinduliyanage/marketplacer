package lk.slt.marketplacer.exceptions;


import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class StoreNotFoundException extends RecordNotFoundException {
    private static final String code = "STORE-404";

    public StoreNotFoundException(String message) {
        super(code, message);
    }
}
