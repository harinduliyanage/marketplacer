package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.InvalidDataException;

public class OrderUnitsException extends InvalidDataException {
    private static final String code = "ORDER-400";

    public OrderUnitsException(String message) {
        super(code, message);
    }
}