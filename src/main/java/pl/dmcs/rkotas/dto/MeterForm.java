package pl.dmcs.rkotas.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MeterForm {

    @Pattern(regexp="^[1-9]$|^[1-9][0-9]$|^(100)$", message = "{error.meter}")
    private String electricity;

    @Pattern(regexp="^[1-9]$|^[1-9][0-9]$|^(100)$", message = "{error.meter}")
    private String coldWater;

    @Pattern(regexp="^[1-9]$|^[1-9][0-9]$|^(100)$", message = "{error.meter}")
    private String heating;

    @Pattern(regexp="^[1-9]$|^[1-9][0-9]$|^(100)$", message = "{error.meter}")
    private String hotWater;

}
