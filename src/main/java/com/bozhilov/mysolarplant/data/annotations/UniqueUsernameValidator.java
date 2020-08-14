package com.bozhilov.mysolarplant.data.annotations;

import com.bozhilov.mysolarplant.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
   @Autowired
   private UserService userService;

   public boolean isValid(String username, ConstraintValidatorContext context) {
      return !this.userService.isUsernameTaken(username);
   }
}
