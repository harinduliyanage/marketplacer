package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class CategoryNotFoundException extends RecordNotFoundException {
    private static final String code = "CATEGORY-404";

    public CategoryNotFoundException(String message) {
        super(code, message);
    }
}
