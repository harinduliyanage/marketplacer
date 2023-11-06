package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RecordNotFoundException {
    private static final String code = "CATEGORY-404";

    public CategoryNotFoundException(String message) {
        super(code, message);
    }
}