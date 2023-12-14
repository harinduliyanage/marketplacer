package lk.slt.marketplacer.exceptions;


import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class AddressNotFoundException extends RecordNotFoundException {
    private static final String code = "Address-404";

    public AddressNotFoundException(String message) {
        super(code, message);
    }
}
