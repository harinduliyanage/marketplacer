package lk.slt.marketplacer.exceptions.base;

public class InvalidDataException extends SystemException {
    public InvalidDataException(String code, String message){
        super(code, message);
    }
}