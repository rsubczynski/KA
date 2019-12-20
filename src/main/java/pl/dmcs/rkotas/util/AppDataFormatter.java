package pl.dmcs.rkotas.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class AppDataFormatter {

    public static String getFormattedDate(LocalDateTime localDateTime){
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .format(localDateTime);
    }
}
