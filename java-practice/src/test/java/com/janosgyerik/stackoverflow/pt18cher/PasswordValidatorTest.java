package com.janosgyerik.stackoverflow.pt18cher;

import org.junit.Test;

import java.util.logging.Logger;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

class PasswordValidator {
    private static final Logger logger = Logger.getLogger(PasswordValidator.class.getSimpleName());

    private final Pattern hasUppercase = Pattern.compile("[A-Z]");
    private final Pattern hasLowercase = Pattern.compile("[a-z]");
    private final Pattern hasNumber = Pattern.compile("\\d");
    private final Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]");

    public String validateNewPass(String pass1, String pass2) {
        if (pass1 == null || pass2 == null) {
            logger.info("Passwords = null");
            return "One or both passwords are null";
        }

        StringBuilder retVal = new StringBuilder();

        if (pass1.isEmpty() || pass2.isEmpty()) {
            retVal.append("Empty fields <br>");
        }

        if (pass1.equals(pass2)) {
            logger.info(pass1 + " = " + pass2);

            if (pass1.length() < 11) {
                logger.info(pass1 + " is length < 11");
                retVal.append("Password is too short. Needs to have 11 characters <br>");
            }

            if (!hasUppercase.matcher(pass1).find()) {
                logger.info(pass1 + " <-- needs uppercase");
                retVal.append("Password needs an upper case <br>");
            }

            if (!hasLowercase.matcher(pass1).find()) {
                logger.info(pass1 + " <-- needs lowercase");
                retVal.append("Password needs a lowercase <br>");
            }

            if (!hasNumber.matcher(pass1).find()) {
                logger.info(pass1 + "<-- needs a number");
                retVal.append("Password needs a number <br>");
            }

            if (!hasSpecialChar.matcher(pass1).find()) {
                logger.info(pass1 + "<-- needs a specail character");
                retVal.append("Password needs a special character i.e. !,@,#, etc.  <br>");
            }
        } else {
            logger.info(pass1 + " != " + pass2);
            retVal.append("Passwords don't match<br>");
        }
        if (retVal.length() == 0) {
            logger.info("Password validates");
            retVal.append("Success");
        }

        return retVal.toString();
    }
}

public class PasswordValidatorTest {
    @Test
    public void test() {
        PasswordValidator validator = new PasswordValidator();
        assertEquals("Passwords don't match<br>", validator.validateNewPass("therejacX", "abc"));
        assertEquals("Password is too short. Needs to have 11 characters <br>", validator.validateNewPass("erejacX1!", "erejacX1!"));
        assertEquals("Success", validator.validateNewPass("hellotherejackX2@", "hellotherejackX2@"));
    }
}