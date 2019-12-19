package pl.dmcs.rkotas.validator;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.dmcs.rkotas.service.AppUserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecretCodeConstrainValidator implements ConstraintValidator<SecretCodeValidator, String> {

    private final AppUserService appUserService;

    public SecretCodeConstrainValidator(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public void initialize(SecretCodeValidator secretCode) {
    }

    public boolean isValid(String secretCode, ConstraintValidatorContext context) {
        UserDetails principal  = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return appUserService.findByEmail(principal.getUsername()).getSecretFlatCode().contains(secretCode);
    }
}
