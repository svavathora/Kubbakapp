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

    /**
     * initialize klasann
     */
    public void initialize(){

    }


    /**
     * Byrjar nýjan Leik
     * @param actionEvent þegar notandi ýtir á takkann
     * @throws IOException kastar IOException
     */
    @FXML
    public void onByrjaLeik(ActionEvent actionEvent) throws IOException { //Þegar ýtum á takkann, á að koma upp til að velja tvo notendur og þaðan inn í leikinn
        //fer þá önnur fxml skrá i staðin fyrir goldrush-view, en var bara að sjá hvort virkaði -sunna

        System.out.println("Byrjum Leik");
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(KubbaKappApplication.class.getResource("goldrush-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("KubbaKapp");
        stage.setScene(scene);
        stage.show();
        Stage nuverandiStage = (Stage) fxByrjaLeik.getScene().getWindow();
        nuverandiStage.close();
    }


    /**
     * Ýtum á stillingartakkann og fáum upp valmynd
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
