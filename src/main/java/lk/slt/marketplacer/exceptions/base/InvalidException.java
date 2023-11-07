package lk.slt.marketplacer.exceptions.base;

public class InvalidException extends SystemException {
    public InvalidException(String code, String message){
        super(code, message);
    }
}