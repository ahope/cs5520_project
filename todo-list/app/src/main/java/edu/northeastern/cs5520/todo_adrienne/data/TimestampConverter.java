package edu.northeastern.cs5520.todo_adrienne.data;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TimestampConverter {

    public static DateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//    DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {
            try {
                return format.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String fromDate(Date value) {
        if (value != null) {
            return format.format(value);
        } else {
            return "";
        }

    }

    @TypeConverter
    public static String fromLocalDateTime(LocalDateTime value) {
        if (value != null) {
            return value.format(localDateTimeFormatter);
        } else {
            return "";
        }
    }

    @TypeConverter
    public static LocalDateTime fromString(String value) {
        if (value != null) {
            if (value.equalsIgnoreCase(""))
                return LocalDateTime.now();
            LocalDateTime thing;
            try {
                thing = LocalDateTime.from(localDateTimeFormatter.parse(value));
            } catch(Exception e) {
                thing = null;
            }
                return thing;

        } else {
            return LocalDateTime.now();
        }
    }
 }
