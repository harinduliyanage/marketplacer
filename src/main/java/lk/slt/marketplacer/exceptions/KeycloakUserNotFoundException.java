package lk.slt.marketplacer.exceptions;

import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;

public class KeycloakUserNotFoundException extends RecordNotFoundException {
    private static final String code = "KEYCLOAK-404";

    public KeycloakUserNotFoundException(String message) {
        super(code, message);
    }
}
