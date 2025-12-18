package com.nuist.setu.killbill.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Date/time helpers based on {@link java.util.Calendar} (works without desugaring).
 */
public final class DateTimeUtils {

    private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private static final SimpleDateFormat MONTH_FMT = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
    private static final SimpleDateFormat TIME_FMT = new SimpleDateFormat("HH:mm", Locale.CHINA);
    private static final SimpleDateFormat DATETIME_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

    private DateTimeUtils() {}

    public static long startOfDay(long timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    public static long endExclusiveOfDay(long timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(startOfDay(timestamp));
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTimeInMillis();
    }

    public static long startOfMonth(long timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    public static long endExclusiveOfMonth(long timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(startOfMonth(timestamp));
        c.add(Calendar.MONTH, 1);
        return c.getTimeInMillis();
    }

    public static String formatDate(long timestamp) {
        return DATE_FMT.format(new Date(timestamp));
    }

    public static String formatMonth(long timestamp) {
        return MONTH_FMT.format(new Date(timestamp));
    }

    public static String formatTime(long timestamp) {
        return TIME_FMT.format(new Date(timestamp));
    }

    public static String formatDateTime(long timestamp) {
        return DATETIME_FMT.format(new Date(timestamp));
    }
}
