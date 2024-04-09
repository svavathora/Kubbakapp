package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import vinnsla.Innskraning;

import java.io.IOException;
import java.util.Optional;

public class UpphafsmyndController {

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


   //Þegar ýtum á takkann, á að koma upp til að velja tvo notendur og þaðan inn í leikinn

    private KubbaKappController kubbaKappController;

    public void initialize(){ //vil setja mynd á stillingarnar-sunna
        System.out.println("Initialize UpphafsmyndController");
        Innskraning innskraning1 = KubbaKappApplication.getLoggedInLeikmadur1();
        if(innskraning1 != null) {
            fxLeikmadur1.setText(innskraning1.getNafn1());
        }
        Innskraning innskraning2 = KubbaKappApplication.getLoggedInLeikmadur2();
        if(innskraning2 != null) {
            fxLeikmadur2.setText(innskraning2.getNafn2());
        }
    }

    //Þegar ýtum á takkann, á að koma upp til að velja tvo notendur og þaðan inn í leikinn
    //fer þá önnur fxml skrá i staðin fyrir goldrush-view, en var bara að sjá hvort virkaði -sunna
    @FXML
    public void onByrjaLeik(ActionEvent actionEvent) throws IOException {
        System.out.println("Byrjum Leik");

        InnskraningDialog dialog = new InnskraningDialog();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(fxByrjaLeik.getScene().getWindow()); // Set the owner of the dialog
        Optional<Pair<String, String>> result = dialog.showAndWait();

        if (result.isPresent()) {
            Pair<String, String> playerNames = result.get();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("goldrush-view.fxml")); // Ensure this path is correct
            Parent root = fxmlLoader.load(); // Load the root

            KubbaKappController controller = fxmlLoader.getController();
            controller.setLeikmennNofn(playerNames.getKey(), playerNames.getValue());

            Stage stage = new Stage();
            stage.setTitle("KubbaKapp");
            stage.setScene(new Scene(root)); // Use the loaded root
            stage.show();

            // Close the current window
            ((Stage) fxByrjaLeik.getScene().getWindow()).close();
        }



       /* InnskraningDialog dialog = new InnskraningDialog(new Innskraning("",""), new Innskraning("",""));
        dialog.initModality(Modality.APPLICATION_MODAL);
        Optional<Pair<String, String>> result = dialog.showAndWait();

        /*result.ifPresent(nofnLeikmanna ->{

        });*/
        //Stage stage = new Stage();
        //FXMLLoader fxmlLoader = new FXMLLoader(KubbaKappApplication.class.getResource("goldrush-view.fxml"));

        //InnskraningDialog dialog = new InnskraningDialog(nyrInnskraning1, nyrInnskraning2);
        //dialog.initModality(Modality.APPLICATION_MODAL);
        //Optional<Innskraning> result = dialog.showAndWait();

        /*result.ifPresent(nofnLeikmanna -> {fxLeikmadur1.setText(nyrInnskraning1.getNafn1());
            KubbaKappApplication.setLoggedInLeikmadur1(nofnLeikmanna);

        });*/


      /*  Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        stage.setTitle("KubbaKapp");
        stage.setScene(scene);
        stage.show();
        Stage nuverandiStage = (Stage) fxByrjaLeik.getScene().getWindow();
        nuverandiStage.close();*/
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
