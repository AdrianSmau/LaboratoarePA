package main.com;

import java.util.Locale;

public class SetLocale {

    public SetLocale(Locale myLocale) {
        Locale.setDefault(myLocale);
        System.out.println("Locale " + myLocale + " set as Default!");
    }
}
