package com.example.vabatahtlikud.validation;

import com.example.vabatahtlikud.infrastructure.exception.DataNotFoundException;

public class ValidationService {

    public static final String ACCOUNT_NOT_EXISTS = "Sellist kontot ei eksisteeri";
    public static final String USER_NOT_EXISTS = "Sellist kasutajat ei eksisteeri";
    public static final String MINIMUM_DEPOSIT_REQUIREMENT = "Miinumum deposiidi nõue";
    public static final String INVALID_LOGIN_CREDENTIALS = "Vale kasutajanimi või parool";
    public static final String INVALID_NEW_USER_CREDENTIALS = "Vale sisend";

    public static void validatePasswordUserExists(Boolean userExists, Boolean status) {
        if (!userExists || !status) {
            throw new DataNotFoundException(INVALID_LOGIN_CREDENTIALS, "Vale kasutajanimi või parool. Proovi uuesti :)");
        }
    }

    public static void validateEmailExists(boolean existsByEmail) {
        if (existsByEmail) {
            throw new DataNotFoundException(INVALID_NEW_USER_CREDENTIALS, "Sellise e-mailiga kasutaja on juba loodud");
        }
    }

    public static void validateUsernameExists(boolean existsByUsername) {
        if (existsByUsername) {
            throw new DataNotFoundException(INVALID_NEW_USER_CREDENTIALS, "Selline kasutajanimi on juba loodud");
        }
    }

    public static void validateEmailAndUsernameExists(boolean existsByEmail, boolean existsByUsername) {
        if (existsByEmail && existsByUsername) {
            throw new DataNotFoundException(INVALID_NEW_USER_CREDENTIALS, "Sellise kasutajanimega ja e-mailiga kasutaja on juba loodud");
        }
    }
}

/*   public static void validateUserExists(Optional<User> user, Integer userId) {
        if (user.isEmpty()) {
            throw new DataNotFoundException(USER_NOT_EXISTS, "Sellist kasutajat ID'ga " + userId + " ei leitud");
        }
    }
    public static void validateAccountExists(Optional<Account> account, Integer accountId) {
        if (account.isEmpty()) {
            throw new DataNotFoundException(ACCOUNT_NOT_EXISTS, "Sellist kontot ID'ga " + accountId + " ei leitud");
        }
    }

    public static void validateAccountExists(Optional<Account> account, String accountNumber) {
        if (account.isEmpty()) {
            throw new DataNotFoundException(ACCOUNT_NOT_EXISTS, "Sellist kontot kontonubriga " + accountNumber + " ei leitud");
        }
    }*/


