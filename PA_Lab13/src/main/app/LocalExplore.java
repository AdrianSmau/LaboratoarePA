package main.app;

import main.com.DisplayLocales;
import main.com.Info;
import main.com.SetLocale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.*;

public class LocalExplore {
    private static final String BASENAME = "main.res.Messages";

    public static void main(String[] args) {
        ResourceBundle messagesEng = getBundle(Locale.US);
        ResourceBundle messagesRo = getBundle(Locale.forLanguageTag("ro"));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Commands:\n\t-locales: Display all available Locales\n\t-locale.set 'locale' (example: locale.set fr_US): sets the default Locale to the one inputted\n\t-info 'locale' (formatted like the 'locale.set' command): displays information about the inputted locale - if nothing is inputted, displays info on default locale\n\t-help: reprints this message\n\t-quit: Quits the application");
            System.out.println(messagesEng.getString("prompt") + " (" + messagesRo.getString("prompt") + ")");
            boolean quit = false;
            while (!quit) {
                System.out.print("cmd>");
                String commandLine = reader.readLine();
                String command = commandLine.split(" ")[0];
                switch (command) {
                    case "quit":
                        quit = true;
                        break;
                    case "help":
                        System.out.println("Commands:\n\t-locales: Display all available Locales\n\t-locale.set 'locale' (example: locale.set fr_US): sets the default Locale to the one inputted\n\t-info 'locale' (formatted like the 'locale.set' command): displays information about the inputted locale - if nothing is inputted, displays info on default locale\n\t-help: reprints this message\n\t-quit: Quits the application");
                        break;
                    case "locales":
                        System.out.println(messagesEng.getString("locales") + " (" + messagesRo.getString("locales") + ")");
                        new DisplayLocales();
                        break;
                    case "locale.set":
                        List<String> components = Arrays.asList(commandLine.split(" "));
                        if (components.size() != 2) {
                            System.out.println("Invalid format! Try again!");
                            quit = true;
                            continue;
                        }
                        String[] tab = components.get(1).split("_");
                        if (tab.length != 2) {
                            System.out.println("Not a valid Locale! Quitting!...");
                            quit = true;
                            continue;
                        }
                        Locale myLocale = new Locale(tab[0], tab[1]);
                        new SetLocale(myLocale);
                        Object[] argument = {Locale.getDefault()};
                        System.out.println(new MessageFormat(messagesEng.getString("locale.set")).format(argument) + " (" + new MessageFormat(messagesRo.getString("locale.set")).format(argument) + ")");
                        break;
                    case "info":
                        List<String> infoComponents = Arrays.asList(commandLine.split(" "));
                        if (infoComponents.size() > 2) {
                            System.out.println("Invalid format! Try again!");
                            quit = true;
                            continue;
                        } else {
                            if (infoComponents.size() == 1) {
                                Object[] infoArgument = {Locale.getDefault()};
                                System.out.println(new MessageFormat(messagesEng.getString("info")).format(infoArgument) + " (" + new MessageFormat(messagesRo.getString("info")).format(infoArgument) + ")");
                                Info.currentLocaleInfo();
                                break;
                            }
                        }
                        String[] infoTab = infoComponents.get(1).split("_");
                        if (infoTab.length != 2) {
                            System.out.println("Not a valid Locale! Quitting!...");
                            quit = true;
                            continue;
                        }
                        Locale myInfoLocale = new Locale(infoTab[0], infoTab[1]);
                        Object[] infoArgument = {myInfoLocale};
                        System.out.println(new MessageFormat(messagesEng.getString("info")).format(infoArgument) + " (" + new MessageFormat(messagesRo.getString("info")).format(infoArgument) + ")");
                        Info.localeInfo(myInfoLocale);
                        break;
                    default:
                        System.out.println(messagesEng.getString("invalid") + " (" + messagesRo.getString("invalid") + ")");
                }
            }
        } catch (IOException ex) {
            System.err.println("[ERROR] IOException caught!");
        }
    }

    private static ResourceBundle getBundle(Locale locale) {
        return ResourceBundle.getBundle(BASENAME, locale);
    }
}
