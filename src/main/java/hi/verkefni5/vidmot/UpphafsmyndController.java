package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class UpphafsmyndController {

    @FXML
    private Button fxByrjaLeik;

    @FXML
    private Button fxStillingar;

    @FXML
    private ImageView fxMyndStillingar;

    private KubbaKappController kubbaKappController;

    public void initialize(){ //vil setja mynd á stillingarnar-sunna
        System.out.println("Initialize NyrLeikurController");
    }

    //Þegar ýtum á takkann, á að koma upp til að velja tvo notendur og þaðan inn í leikinn
    //fer þá önnur fxml skrá i staðin fyrir goldrush-view, en var bara að sjá hvort virkaði -sunna
    @FXML
    public void onByrjaLeik(ActionEvent actionEvent) throws IOException {
        System.out.println("Byrjum Leik");
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(KubbaKappApplication.class.getResource("goldrush-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        //FXML_Lestur.lesa(this,"goldrush-view.fxml"); -skil ekki alveg hvernig þú notar þetta en 100% hægt að nota frekar
        //til að gera þetta betri kóða -sunna
        //Scene scene = new Scene(fxmlLoader.load(), 1000, 800);

        stage.setTitle("KubbaKapp");
        stage.setScene(scene);
        stage.show();
        Stage nuverandiStage = (Stage) fxByrjaLeik.getScene().getWindow();
        nuverandiStage.close();
    }


    /**
     * Ýtum á stillingartakkann (vantar mynd) og fáum upp valmynd
     *
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onStillingar(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(KubbaKappApplication.class.getResource("valmynd-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 202, 300);
        stage.setScene(scene);
        stage.show();

    }

}
