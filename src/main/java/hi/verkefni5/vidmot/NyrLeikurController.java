package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vinnsla.Innskraning;

import java.io.IOException;
import java.util.Optional;

public class NyrLeikurController {

    @FXML
    private Button fxByrjaLeik;

    @FXML
    private Button fxStillingar;

    @FXML
    private ImageView fxMyndStillingar;

    @FXML
    private Label fxLeikmadur1;

    @FXML
    private Label fxLeikmadur2;

    /*
    public void initialize(){ //reyna að setja mynd á stillingarnar-sunna
      //  Image image = new Image("/media/stillingar.png");
      //  fxMyndStillingar.setImage(image);
    }

     */

    public void initialize() {
        Innskraning innskraning1 = GoldApplication.getLoggedInLeikmadur1();
        if(innskraning1 != null) {
            fxLeikmadur1.setText(innskraning1.getNafn1());
        }
        Innskraning innskraning2 = GoldApplication.getLoggedInLeikmadur2();
        if(innskraning2 != null) {
            fxLeikmadur2.setText(innskraning2.getNafn2());
        }
    }

   //Þegar ýtum á takkann, á að koma upp til að velja tvo notendur og þaðan inn í leikinn
    //fer þá önnur fxml skrá i staðin fyrir goldrush-view, en var bara að sjá hvort virkaði -sunna
    @FXML
    public void onByrjaLeik(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(GoldApplication.class.getResource("goldrush-view.fxml"));
        Innskraning nyrInnskraning1 = new Innskraning("", "");
        Innskraning nyrInnskraning2 = new Innskraning("", "");
        InnskraningDialog dialog = new InnskraningDialog(nyrInnskraning1, nyrInnskraning2);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Optional<Innskraning> result = dialog.showAndWait();

        result.ifPresent(innskraning1 -> {fxLeikmadur1.setText(nyrInnskraning1.getNafn1());
            GoldApplication.setLoggedInLeikmadur1(innskraning1);

        });
        result.ifPresent(innskraning2 -> {fxLeikmadur2.setText(nyrInnskraning2.getNafn2());
            GoldApplication.setLoggedInLeikmadur2(innskraning2);
        });






        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("KubbaKapp");
        stage.setScene(scene);
        stage.show();
        Stage nuverandiStage = (Stage) fxByrjaLeik.getScene().getWindow();
        nuverandiStage.close();
    }

    /**
     * Ýtum á stillingartakkann (vantar mynd) og fáum upp valmynd
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onStillingar(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(GoldApplication.class.getResource("valmynd-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 202, 300);
        stage.setScene(scene);
        stage.show();

    }

}
