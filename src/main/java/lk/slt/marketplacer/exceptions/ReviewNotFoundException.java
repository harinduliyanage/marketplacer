package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class ReviewNotFoundException extends RecordNotFoundException {
    private static final String code = "REVIEW-404";

    public ReviewNotFoundException(String message) {
        super(code, message);
    }
}
