package lk.slt.marketplacer.exceptions.base;

public class RecordNotFoundException extends SystemException{

    public RecordNotFoundException(String code, String message){
        super(code, message);
    }
}
