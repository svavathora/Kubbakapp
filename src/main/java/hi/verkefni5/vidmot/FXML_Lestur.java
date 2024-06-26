package hi.verkefni5.vidmot;

import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class FXML_Lestur {

    /**
     * Fxml skráin lesin inn
     * @param controller controller klasinn fyrir fxml skrána
     * @param fxmlSkra fxml skráin sem á að birta
     */
    public static void lesa(Object controller, String fxmlSkra) {
        FXMLLoader fxmlLoader = new FXMLLoader(controller.getClass().getResource(fxmlSkra));
        fxmlLoader.setClassLoader(controller.getClass().getClassLoader());
        fxmlLoader.setRoot(controller);
        fxmlLoader.setController(controller);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
