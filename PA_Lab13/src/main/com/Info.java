package main.com;

import java.text.DateFormatSymbols;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;

public class Info {
    public static void currentLocaleInfo() {
        try {
            System.out.println("Country - " + Locale.getDefault().getDisplayCountry(Locale.US) + "(" + Locale.getDefault().getDisplayCountry(Locale.getDefault()) + ")");
            System.out.println("Language - " + Locale.getDefault().getDisplayLanguage(Locale.US) + "(" + Locale.getDefault().getDisplayLanguage(Locale.getDefault()) + ")");
            System.out.println("Currency - " + Currency.getInstance(Locale.getDefault()).toString() + "(" + Currency.getInstance(Locale.getDefault()).getDisplayName(Locale.getDefault()) + ")");
            System.out.println("Week days: ");
            for (String x : DateFormatSymbols.getInstance(Locale.getDefault()).getWeekdays()) {
                if (!x.equals(""))
                    System.out.println("\t" + x);
            }
            System.out.println("Months: ");
            for (String x : DateFormatSymbols.getInstance(Locale.getDefault()).getMonths()) {
                if (!x.equals(""))
                    System.out.println("\t" + x);
            }
            ZonedDateTime zoned = ZonedDateTime.now();
            DateTimeFormatter patternEng = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.US);
            DateTimeFormatter pattern = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.getDefault());
            System.out.println("Today - " + zoned.format(patternEng) + " (" + zoned.format(pattern) + ")");
        } catch (IllegalArgumentException ex) {
            System.err.println("[ERROR] Invalid Locale!");
            System.exit(1);
        }
    }

    public static void localeInfo(Locale locale) {
        try {
            System.out.println("Country - " + locale.getDisplayCountry(Locale.US) + "(" + locale.getDisplayCountry(locale) + ")");
            System.out.println("Language - " + locale.getDisplayLanguage(Locale.US) + "(" + locale.getDisplayLanguage(locale) + ")");
            System.out.println("Currency - " + Currency.getInstance(locale).toString() + "(" + Currency.getInstance(locale).getDisplayName(locale) + ")");
            System.out.println("Week days: ");
            for (String x : DateFormatSymbols.getInstance(locale).getWeekdays()) {
                if (!x.equals(""))
                    System.out.println("\t" + x);
            }
            System.out.println("Months: ");
            for (String x : DateFormatSymbols.getInstance(locale).getMonths()) {
                if (!x.equals(""))
                    System.out.println("\t" + x);
            }
            ZonedDateTime zoned = ZonedDateTime.now();
            DateTimeFormatter patternEng = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.US);
            DateTimeFormatter pattern = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
            System.out.println("Today - " + zoned.format(patternEng) + " (" + zoned.format(pattern) + ")");
        } catch (IllegalArgumentException ex) {
            System.err.println("[ERROR] Invalid Locale!");
            System.exit(1);
        }
    }
}
