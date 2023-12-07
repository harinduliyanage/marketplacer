package lk.slt.marketplacer.util;

public class Constants {

    private Constants() {
        throw new IllegalStateException("constants utils can not use for making objects");
    }

    public static final String USER_NOT_FOUND_MSG = "User '%s' not found";
    public static final String USERNAME_ALREADY_EXISTS_MSG = "User already exists given username '%s'";
    public static final String EMAIL_ALREADY_EXISTS_MSG = "User already exists given email '%s'";
    public static final String USERNAME_INVALID_MSG = "Invalid username '%s'";
    public static final String STORE_NOT_FOUND_MSG = "Store '%s' not found of user '%s'";
    public static final String STORE_ALREADY_EXISTS_MSG = "Store already exists given %s '%s'";
    public static final String INVALID_STORE_ID_MSG = "Invalid given store id '%s'";
    public static final String PRODUCT_NOT_FOUND_OF_STORE_MSG = "Product '%s' not found of store '%s'";
    public static final String PRODUCT_NOT_FOUND_MSG = "Product not found given id '%s'";
    public static final String CATEGORY_NOT_FOUND_MSG = "Category '%s' not found";
    public static final String CATEGORY_ALREADY_EXISTS_MSG = "Category already exists given name '%s'";
    public static final String INVALID_CATEGORY_TYPE_MSG = "Invalid category type of given category id '%s'";
    public static final String ORDER_NOT_FOUND_MSG = "Order '%s' not found of user '%s'";
    public static final String ORDER_DETAILS_NOT_FOUND_MSG = "Order Details '%s' not found of order '%s'";
    public static final String ADDRESS_NOT_FOUND_MSG = "Can't found address for given user id '%s' , address id '%s'";
    public static final String CART_NOT_FOUND_MSG = "Cart '%s' not found of user '%s'";
    public static final String INVALID_DISCOUNT_MSG = "The discount amount must be less than the price";
    public static final String ORDER_NULL_ATTRIBUTE_MSG = "userId & cartId must be not null or orderDetails must be not null";

}
