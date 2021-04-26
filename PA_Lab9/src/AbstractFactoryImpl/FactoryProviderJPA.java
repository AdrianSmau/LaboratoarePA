package AbstractFactoryImpl;

public class FactoryProviderJPA {

    public static AbstractFactoryJPA getFactory(String factoryType) {
        switch (factoryType) {
            case "Movie":
                return new MovieFactoryJPA();
            case "Actor":
                return new ActorFactoryJPA();
            case "Genre":
                return new GenreFactoryJPA();
            case "Director":
                return new DirectorFactoryJPA();
            default:
                System.out.println(" [ERROR] Invalid Factory Type!...");
                return null;
        }
    }
}
