package com.example.vabatahtlikud.validation;



import com.example.vabatahtlikud.infrastructure.exception.BusinessException;
import com.example.vabatahtlikud.infrastructure.exception.DataNotFoundException;

import java.util.List;
import java.util.Optional;

public class ValidationService {

    public static final String ACCOUNT_NOT_EXISTS = "Sellist kontot ei eksisteeri";
    public static final String USER_NOT_EXISTS = "Sellist kasutajat ei eksisteeri";
    public static final String INSUFFICIENT_FUNDS = "Kontol pole piisavalt vahendeid tehingu sooritamiseks";
    public static final String MINIMUM_DEPOSIT_REQUIREMENT = "Miinumum deposiidi nõue";
    public static final Integer MINIMUM_DEPOSIT_AMOUNT = 5;
    public static final String INVALID_LOGIN_CREDENTIALS = "Vale kasutajanimi või parool";

/*    public static void validateUserExists(Optional<User> user, Integer userId) {
        if (user.isEmpty()) {
            throw new DataNotFoundException(USER_NOT_EXISTS, "Sellist kasutajat ID'ga " + userId + " ei leitud");
        }
    }
    public static void validatePasswordUserExists(List<UserRole> userRoles) {
        if (userRoles.isEmpty()) {
            throw new DataNotFoundException(INVALID_LOGIN_CREDENTIALS, "Vale kasutajanimi või parool. Proovi uuesti :)");
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
    }

    public static void validateEnoughBalance(Integer amount, Account account) {
        if (account.getBalance() - amount < 0) {
            throw new BusinessException(INSUFFICIENT_FUNDS, "Kontol " + account.getAccountNumber() +
                    " pole piisavalt vahendeid. Kontojääk on: " + account.getBalance());
        }
    }

    public static void validateDepositAmount(Integer amount) {
        if (amount < MINIMUM_DEPOSIT_AMOUNT) {
            throw new BusinessException(MINIMUM_DEPOSIT_REQUIREMENT, "Miinimum deposiit alates €" + MINIMUM_DEPOSIT_AMOUNT);
        }
    }*/

}
