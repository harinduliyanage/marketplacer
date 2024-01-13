package lk.slt.marketplacer.exceptions.base;

public class CategoryIdInvalidException extends InvalidDataException {
    private static final String code = "CATEGORY-400";

    public CategoryIdInvalidException(String message) {
        super(code, message);
    }
}