package com.bozhilov.mysolarplant.data.annotations;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
   private String firstFieldName;
   private String secondFieldName;
   private String message;

   @Override
   public void initialize(FieldMatch constraintAnnotation) {
      this.firstFieldName = constraintAnnotation.firstField();
      this.secondFieldName = constraintAnnotation.secondField();
      this.message = constraintAnnotation.message();
   }

   public boolean isValid(Object object, ConstraintValidatorContext context) {
      boolean isValid=true;
      Object firstFieldValue = new BeanWrapperImpl(object).getPropertyValue(firstFieldName);
      Object secondFieldValue = new BeanWrapperImpl(object).getPropertyValue(secondFieldName);

      isValid = firstFieldValue==null&& secondFieldValue==null ||
              firstFieldValue != null && firstFieldValue.equals(secondFieldValue);

      if(!isValid){
         context.buildConstraintViolationWithTemplate(message)
                 .addPropertyNode(secondFieldName)
                 .addConstraintViolation()
                 .disableDefaultConstraintViolation();
      }
      return isValid;
   }
}
