package com.employees.employees.application.validation;

import java.util.List;

public final class SupportedDateFormats {
    private static final List<String> supportedDateFormats = List.of(
            "yyyy-MM-dd",
            "dd/MM/yyyy",
            "MM/dd/yyyy",
            "dd-MM-yyyy",
            "MM-dd-yyyy",
            "dd.MM.yyyy",
            "yyyy/MM/dd",
            "yyyy.MM.dd"
    );

    public static boolean isSupported(String dateFormat) {
        if (dateFormat == null) {
            return false;
        }

        return supportedDateFormats.contains(dateFormat);
    }
}
