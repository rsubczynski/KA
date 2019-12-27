package pl.dmcs.rkotas.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
public class AppDataFormatter {

    public String getFormattedDate(LocalDateTime localDateTime){
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .format(localDateTime);
    }
}
