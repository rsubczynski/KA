package pl.dmcs.rkotas.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class RegisterForm {

    @Email(message = "{error.register.email}")
    @NotBlank(message = "{error.required}")
    @Size(min=2, max=30, message = "{error.length.regexp}")
    private String email;

    @Size(min=2, max=30, message = "{error.length.regexp}")
    @NotBlank(message = "{error.required}")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
