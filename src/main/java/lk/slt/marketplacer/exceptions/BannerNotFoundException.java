package lk.slt.marketplacer.exceptions;


import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class BannerNotFoundException extends RecordNotFoundException {
    private static final String code = "BANNER-404";

    public BannerNotFoundException(String message) {
        super(code, message);
    }
}
