package pl.dmcs.rkotas.validator;

import pl.dmcs.rkotas.service.AppUserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserConstrainValidator implements ConstraintValidator<ExistUserValidator, String> {

   private String userEmail;

   private final AppUserService appUserService;

   public UserConstrainValidator(AppUserService appUserService) {
      this.appUserService = appUserService;
   }

   public void initialize(ExistUserValidator email) {
      userEmail = email.value();
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return appUserService.isExistByEmail(userEmail);
   }

}
