package pl.dmcs.rkotas.validator;

import pl.dmcs.rkotas.authentication.AuthenticationFacade;
import pl.dmcs.rkotas.service.AppUserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecretCodeConstrainValidator implements ConstraintValidator<SecretCodeValidator, String> {

    private final AppUserService appUserService;
    private final AuthenticationFacade authenticationFacade;

    public SecretCodeConstrainValidator(AppUserService appUserService, AuthenticationFacade authenticationFacade) {
        this.appUserService = appUserService;
        this.authenticationFacade = authenticationFacade;
    }

    public void initialize(SecretCodeValidator secretCode) {
    }

    public boolean isValid(String secretCode, ConstraintValidatorContext context) {
        return appUserService.findByEmail(
                authenticationFacade.getLoginUser().getUsername()).getSecretFlatCode().contains(secretCode);
    }
}
