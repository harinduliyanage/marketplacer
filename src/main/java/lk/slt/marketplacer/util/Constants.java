package lk.slt.marketplacer.util;

public class Constants {

    private Constants() {
        throw new IllegalStateException("constants utils can not use for making objects");
    }

    public static final String USER_NOT_FOUND_MSG = "Username already existing by given '%s'";
    public static final String USERNAME_ALREADY_EXISTS = "User '%s' not found";
    public static final String STORE_NOT_FOUND_MSG = "Store '%s' not found of user '%s'";
    public static final String PRODUCT_NOT_FOUND_MSG = "Product '%s' not found of store '%s'";

}
