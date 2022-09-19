package com.example.vabatahtlikud.validation;

import com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date.VolunteerEventDate;
import com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date.VolunteerEventDateInfo;
import com.example.vabatahtlikud.infrastructure.exception.DataNotFoundException;

import java.time.LocalDate;
import java.util.List;

public class ValidationService {

    public static final String ACCOUNT_NOT_EXISTS = "Sellist kontot ei eksisteeri";
    public static final String USER_NOT_EXISTS = "Sellist kasutajat ei eksisteeri";
    public static final String MINIMUM_DEPOSIT_REQUIREMENT = "Miinumum deposiidi nõue";
    public static final String INVALID_LOGIN_CREDENTIALS = "Vale kasutajanimi või parool";
    public static final String INVALID_INPUT = "Vale sisend";

    public static void validatePasswordUserExists(Boolean userExists, Boolean status) {
        if (!userExists || !status) {
            throw new DataNotFoundException(INVALID_LOGIN_CREDENTIALS, "Vale kasutajanimi või parool. Proovi uuesti :)");
        }
    }

    public static void validateEmailExists(boolean existsByEmail) {
        if (existsByEmail) {
            throw new DataNotFoundException(INVALID_INPUT, "Sellise e-mailiga kasutaja on juba loodud");
        }
    }

    public static void validateUsernameExists(boolean existsByUsername) {
        if (existsByUsername) {
            throw new DataNotFoundException(INVALID_INPUT, "Selline kasutajanimi on juba loodud");
        }
    }

    public static void validateEmailAndUsernameExists(boolean existsByEmail, boolean existsByUsername) {
        if (existsByEmail && existsByUsername) {
            throw new DataNotFoundException(INVALID_INPUT, "Sellise kasutajanimega ja e-mailiga kasutaja on juba loodud");
        }
    }

    public static void validateTaskExists(boolean existsByNameAndEventIdAndStatus) {
        if (existsByNameAndEventIdAndStatus) {
            throw new DataNotFoundException(INVALID_INPUT, "Selline ülesanne on juba loodud");
        }
    }

    public static void validateAdditionalInfoExists(boolean existsByNameAndEventIdAndStatus) {
        if (existsByNameAndEventIdAndStatus) {
            throw new DataNotFoundException(INVALID_INPUT, "Selline lisainfo on juba olemas");
        }
    }

    public static void validateDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new DataNotFoundException(INVALID_INPUT, "Lõpu kuupäev ei saa olla enne alguskuupäeva");
        } else if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
            throw new DataNotFoundException(INVALID_INPUT, "Sisestatud kuupäev ei saa olla enne tänast kuupäeva");
        }
    }

    public static void validateVolunteersRequired(Integer volunteersRequired) {
        if (volunteersRequired < 1) {
            throw new DataNotFoundException(INVALID_INPUT, "Vabatahtlike arv peab olema vähemalt 1");
        }
    }

    public static void validateDateSelectionExists(List<VolunteerEventDateInfo> volunteerEventDateInfos) {
        if (volunteerEventDateInfos.isEmpty()) {
            throw new DataNotFoundException(INVALID_INPUT, "Vähemalt üks kuupäev peab olema valitud");
        }
    }

    public static void validateExceedingAttendance(double a, double b, LocalDate date) {
        if ((a / b) > 1.1) {
            throw new DataNotFoundException(INVALID_INPUT, "Kuupäevale " + date + " ei saa registreerida rohkem kui 10% maksimaalsest täituvusest.");
        }
    }

    public static void validateRegistrationExists() {
        throw new DataNotFoundException(INVALID_INPUT, "Selle kasutajaga on juba antud üritusele registreeritud.");
    }

    public static void validateUserCreation(boolean emptyUsername, boolean emptyPassword) {
        if (emptyUsername || emptyPassword) {
            throw new DataNotFoundException(INVALID_INPUT, "Kasutajanimi või parool ei saa olla tühjad");
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


