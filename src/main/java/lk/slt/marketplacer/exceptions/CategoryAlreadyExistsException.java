package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CategoryAlreadyExistsException extends SystemException {
    private static final String code = "Category-400";
    public CategoryAlreadyExistsException(String message) {
        super(code, message);
    }
}
