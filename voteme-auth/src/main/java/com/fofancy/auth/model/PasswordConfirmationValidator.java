package com.fofancy.auth.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by User on 16.07.2017.
 */
public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, UserSignupDto> {
    @Override
    public void initialize(PasswordConfirmation passwordConfirmation) {

    }

    @Override
    public boolean isValid(UserSignupDto user, ConstraintValidatorContext constraintValidatorContext) {
        return user.getPassword().equals(user.getPasswordConfirm());
    }
}
