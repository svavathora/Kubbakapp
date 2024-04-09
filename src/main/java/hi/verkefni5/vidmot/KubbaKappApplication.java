package hi.verkefni5.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vinnsla.Innskraning;

import java.io.IOException;

public class KubbaKappApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(KubbaKappApplication.class.getResource("upphafsmynd-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("KubbaKapp");
        stage.setScene(scene);
        stage.show();

    }
    private static Innskraning loggedInLeikmadur1;

    private static Innskraning loggedInLeikmadur2;

    /**
     * getter fyrir innskráðan áskrifanda
     * @return innskráður áskrifandi
     */
    public static Innskraning getLoggedInLeikmadur1() {
        return loggedInLeikmadur1;
    }

    public static Innskraning getLoggedInLeikmadur2() {
        return loggedInLeikmadur2;
    }


    public static void setLoggedInLeikmadur1(Innskraning innskraning1) {
        KubbaKappApplication.loggedInLeikmadur1 = innskraning1;
    }

    public static void setLoggedInLeikmadur2(Innskraning innskraning2) {
        KubbaKappApplication.loggedInLeikmadur2 = innskraning2;
    }
}
