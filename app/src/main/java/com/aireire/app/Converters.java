package com.aireire.app;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDate;

public class Converters {
    @TypeConverter
    public static LocalDate stringToLocalDate(String date) {
        return date == null ? null : LocalDate.parse(date);
    }

    @TypeConverter
    public static String localDateToString(LocalDate date) {
        return date == null ? null : date.toString();
    }
}
