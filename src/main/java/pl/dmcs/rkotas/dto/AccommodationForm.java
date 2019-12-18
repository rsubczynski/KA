package pl.dmcs.rkotas.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AccommodationForm {

    @NotBlank(message = "{error.required}")
    @Size(min=2, max=30, message = "{error.length.regexp}")
    private String firstName;

    @NotBlank(message = "{error.required}")
    @Size(min=2, max=30, message = "{error.length.regexp}")
    private String lastName;

    @NotBlank(message = "{error.required}")
    @Pattern(regexp="(^$|[0-9]{9})", message = "{error.invalid.phoneNumber}")
    private String telephone;

    @NotBlank(message = "{error.required}")
    private String secretCode;
}
