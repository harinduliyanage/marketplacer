package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StoreAlreadyExistsException extends SystemException {
    private static final String code = "Store-400";
    public StoreAlreadyExistsException(String message) {
        super(code, message);
    }
}
