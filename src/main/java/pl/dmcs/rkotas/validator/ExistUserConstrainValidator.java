package pl.dmcs.rkotas.validator;

import pl.dmcs.rkotas.service.AppUserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistUserConstrainValidator implements ConstraintValidator<ExistUserValidator, String> {

   private final AppUserService appUserService;

   public ExistUserConstrainValidator(AppUserService appUserService) {
      this.appUserService = appUserService;
   }

   public void initialize(ExistUserValidator email) {
   }

   public boolean isValid(String userEmail, ConstraintValidatorContext context) {
      return !appUserService.isExistByEmail(userEmail);
   }

}
