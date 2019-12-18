package pl.dmcs.rkotas.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.dmcs.rkotas.validator.ExistUserValidator;

import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterForm {

    @Email(message = "{error.register.email}")
    @NotBlank(message = "{error.required}")
    @Size(min=2, max=30, message = "{error.length.regexp}")
    @ExistUserValidator(message = "{error.userExist}")
    private String email;

    @Size(min=2, max=30, message = "{error.length.regexp}")
    @NotBlank(message = "{error.required}")
    private String password;

}
