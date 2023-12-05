package lk.slt.marketplacer.exceptions;


import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class AddressFoundException extends RecordNotFoundException {
    private static final String code = "Address-404";

    public AddressFoundException(String message) {
        super(code, message);
    }
}
