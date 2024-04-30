package at.technikum.firstui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMLDependencyInjector {

    public static Parent load(String location, Locale locale) throws IOException {
        FXMLLoader loader = loader(location, locale);

        return loader.load();
    }

    public static FXMLLoader loader(String location, Locale locale) {
        return new FXMLLoader(
                FXMLDependencyInjector.class.getResource("/at/technikum/firstui/" + location),
                ResourceBundle.getBundle("at.technikum.firstui." + "gui_strings", locale),
                new JavaFXBuilderFactory(),
                viewClass -> ViewFactory.getInstance().create(viewClass)
        );
    }
}
