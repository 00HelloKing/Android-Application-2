package com.nuist.setu.killbill.util;

import java.util.Locale;

public final class MoneyUtils {
    private MoneyUtils() {}

    public static String formatCny(double amount) {
        return String.format(Locale.CHINA, "￥%.2f", amount);
    }
}
