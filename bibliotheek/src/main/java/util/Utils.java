package util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Utils {
    public static Date dateVanString(final String datum){
        if(datum.equalsIgnoreCase("gisteren")){
          return Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
        }else {
            return Date.from(Instant.parse(datum + "T00:00:00.000Z"));
        }
    }

    public static Instant addDate(Instant date, int workdays) {
        if (workdays < 1) {
            return date;
        }

        LocalDate result = LocalDate.ofInstant(date, ZoneId.systemDefault());
        int addedDays = 0;
        while (addedDays < workdays) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
}
