package Helper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {

    public static String formatDateTime(LocalDateTime dateTime) {
        
        if(dateTime == null){
            return null;
        }
        
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);

        if (duration.toMinutes() < 1) {
            return duration.getSeconds() + " seconds ago";
        } else if (duration.toHours() < 1) {
            return duration.toMinutes() + " minutes ago";
        } else if (duration.toHours() < 3) {
            return duration.toHours() + " hours ago";
        } else if (duration.toDays() < 1) {
            return "Today at " + dateTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } else if (duration.toDays() < 2) {
            return "Yesterday";
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        }
    }
}



