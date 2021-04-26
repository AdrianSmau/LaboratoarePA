package AbstractFactoryImpl;

public class FactoryProviderJDBC {
    public static AbstractFactoryJDBC getFactory(String factoryType) {
        switch (factoryType) {
            case "Movie":
                return new MovieFactoryJDBC();
            case "Actor":
                return new ActorFactoryJDBC();
            case "Genre":
                return new GenreFactoryJDBC();
            case "Director":
                return new DirectorFactoryJDBC();
            default:
                System.out.println(" [ERROR] Invalid Factory Type!...");
                return null;
        }
    }
}
