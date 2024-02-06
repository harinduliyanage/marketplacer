package lk.slt.marketplacer.util;

public class Constants {

    private Constants() {
        throw new IllegalStateException("constants utils can not use for making objects");
    }

    public static final String USER_NOT_FOUND_MSG = "User '%s' not found";
    public static final String USERNAME_ALREADY_EXISTS_MSG = "User already exists given username '%s'";
    public static final String EMAIL_ALREADY_EXISTS_MSG = "User already exists given email '%s'";
    public static final String USERNAME_INVALID_MSG = "Invalid username '%s'";
    public static final String STORE_NOT_FOUND_OF_USER_MSG = "Store '%s' not found of user '%s'";
    public static final String STORE_NOT_FOUND_MSG = "Store not found given id '%s'";
    public static final String STORE_STATUS_INVALID_MSG = "'%s' can not be assign to unapproved store";
    public static final String STORE_ALREADY_EXISTS_MSG = "Store already exists given %s '%s'";
    public static final String INVALID_STORE_ID_MSG = "Invalid given store id '%s'";
    public static final String PRODUCT_NOT_FOUND_OF_STORE_MSG = "Product '%s' not found of store '%s'";
    public static final String PRODUCT_NOT_FOUND_MSG = "Product not found given id '%s'";
    public static final String PRODUCT_STORE_NOT_APPROVED_MSG = "Store '%s' is in review. Before inserting products The store must be approved.";
    public static final String INVALID_CATEGORY_ID_MSG = "Invalid given category id '%s'";
    public static final String CATEGORY_ID_ALREADY_EXISTS_MSG = "Category already exists given %s '%s'";
    public static final String CATEGORY_NOT_FOUND_MSG = "Category '%s' not found";
    public static final String CATEGORY_NAME_ALREADY_EXISTS_MSG = "Category already exists given name '%s'";
    public static final String INVALID_CATEGORY_TYPE_MSG = "Invalid category type of given category id '%s'";
    public static final String ORDER_NOT_FOUND_MSG = "Order '%s' not found of user '%s'";
    public static final String ORDER_DETAILS_NOT_FOUND_MSG = "Order Details '%s' not found of order '%s'";
    public static final String ORDER_UNITS_OUT_OF_RANGE_MSG = "Product '%s' available units less than '%s'";
    public static final String ADDRESS_NOT_FOUND_MSG = "Can't found address for given user id '%s', address id '%s'";
    public static final String BANNER_NOT_FOUND_MSG = "Can't found banner for id '%s'";
    public static final String CART_NOT_FOUND_MSG = "Cart '%s' not found of user '%s'";
    public static final String INVALID_DISCOUNT_MSG = "The discount amount must be less than the price";
    public static final String ORDER_NULL_ATTRIBUTE_MSG = "User id and cart id must be not empty or order details must be not empty";
    public static final String CART_EMPTY_MSG = "Cart is empty!";
    public static final String CART_FORBIDDEN_MSG = "You don't have permission to access cart id '%s'";
    public static final String ORDER_ADDRESS_REQUIRED_MSG = "For %s, '%s' & '%s' are required to order";
    public static final String REVIEW_NOT_FOUND_MSG = "Review '%s' not found of user '%s'";
    public static final String WISHLIST_NOT_FOUND_MSG = "Wishlist '%s' not found of user '%s'";

    public static final String KEYCLOAK_USER_NOT_FOUND_MSG = "User not found in Keycloak";

}
