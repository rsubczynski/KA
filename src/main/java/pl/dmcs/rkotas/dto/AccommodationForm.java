package pl.dmcs.rkotas.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccommodationForm {

    @NotBlank(message = "{error.required}")
    @Size(min=2, max=30, message = "{error.length.regexp}")
    private String firstName;

    @NotBlank(message = "{error.required}")
    @Size(min=2, max=30, message = "{error.length.regexp}")
    private String lastName;

    @NotBlank(message = "{error.required}")
    @Pattern(regexp="(^$|[0-9]{9})", message = "invalid phone number")
    private String telephone;

    @NotBlank(message = "{error.required}")
    private String secretCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }
}
