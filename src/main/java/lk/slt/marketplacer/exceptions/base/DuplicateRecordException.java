package lk.slt.marketplacer.exceptions.base;

public class DuplicateRecordException extends SystemException {
    public DuplicateRecordException(String code, String message){
        super(code, message);
    }
}
