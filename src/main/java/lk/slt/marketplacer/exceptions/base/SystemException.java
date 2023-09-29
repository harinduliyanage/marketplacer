package lk.slt.marketplacer.exceptions.base;

public class SystemException extends RuntimeException{

    private String errorCode="SYS-0000";

    public SystemException(String message) {
        super(message);
        this.errorCode="SYS-0000";
    }

    public SystemException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
