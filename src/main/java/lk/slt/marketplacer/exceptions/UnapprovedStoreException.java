package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidDataException;

public class UnapprovedStoreException extends InvalidDataException {
    private static final String code = "PRODUCT-400";

    public UnapprovedStoreException(String message) {
        super(code, message);
    }
}