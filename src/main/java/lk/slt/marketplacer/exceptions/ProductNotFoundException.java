package lk.slt.marketplacer.exceptions;


import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class ProductNotFoundException extends RecordNotFoundException {
    private static final String code = "PRODUCT-404";

    public ProductNotFoundException(String message) {
        super(code, message);
    }
}
