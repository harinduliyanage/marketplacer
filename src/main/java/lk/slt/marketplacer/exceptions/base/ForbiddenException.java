package lk.slt.marketplacer.exceptions.base;

public class ForbiddenException extends SystemException{
    public ForbiddenException(String code, String message){
        super(code, message);
    }
}
