package com.bozhilov.mysolarplant.data.annotations;

import com.bozhilov.mysolarplant.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private UserService userService;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        //return !this.userService.isEmailTaken(email);
        return true;
        //todo unique email validation
    }
}
