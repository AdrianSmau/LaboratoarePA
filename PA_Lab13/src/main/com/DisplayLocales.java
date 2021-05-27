package main.com;

import java.util.Locale;

public class DisplayLocales {
    static {
        System.out.println("Default Locale: " + Locale.getDefault());
        System.out.println("All available Locales: ");
        for (Locale locale : Locale.getAvailableLocales()) {
            if (!locale.getDisplayCountry().equals("") && !locale.getDisplayLanguage(locale).equals(""))
                System.out.println("\t-> " + locale.getDisplayCountry(Locale.US) + " (" + locale.getDisplayCountry() + ")" + " - " + locale.getDisplayLanguage(locale));
        }
    }
}
