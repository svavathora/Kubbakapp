package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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

    /**
     * Þegar upphafsmyndin keyrð þá er náð í nöfn notanda
     */
    public void initialize() {
        Innskraning innskraning1 = KubbaKappApplication.getLoggedInLeikmadur1();
        if (innskraning1 != null) {
            fxLeikmadur1.setText(innskraning1.getNafn1());
        }
        Innskraning innskraning2 = KubbaKappApplication.getLoggedInLeikmadur2();
        if (innskraning2 != null) {
            fxLeikmadur2.setText(innskraning2.getNafn2());
        }
    }


    /**
     * Þegar ýtt er á Byrja leik þá kemur upp dialog fyrir notanda að skrá sig inn með nöfnum
     * Þegar búið er að skýra notendur þá getur notandi hafið leik og spilaborðið kemur upp
     *
     * @param actionEvent ýtt á byrja leik takkann
     * @throws IOException
     */
    @FXML
    public void onByrjaLeik(ActionEvent actionEvent) throws IOException {
        InnskraningDialog dialog = new InnskraningDialog();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(fxByrjaLeik.getScene().getWindow());
        Optional<Pair<String, String>> result = dialog.showAndWait();

        if (result.isPresent()) {
            Pair<String, String> playerNames = result.get();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("goldrush-view.fxml"));
            Parent root = fxmlLoader.load();

            KubbaKappController controller = fxmlLoader.getController();
            controller.setLeikmennNofn(playerNames.getKey(), playerNames.getValue());

            Stage stage = new Stage();
            stage.setTitle("KubbaKapp");
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) fxByrjaLeik.getScene().getWindow()).close();
        }

    }

    /**
     * Ýtum á stillingartakkann og fáum upp valmynd
     *
     * @param actionEvent
     */
    @FXML
    public void onStillingar(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("valmynd-view.fxml"));
            VBox content = fxmlLoader.load();
            Dialog<Void> dialog = new Dialog<>();

            DialogPane dialogPane = new DialogPane();
            dialogPane.setContent(content);
            dialog.setDialogPane(dialogPane);

            dialog.initOwner(primaryStage);
            dialog.initModality(Modality.APPLICATION_MODAL);

            dialog.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onUmForritid(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um forritið");
        alert.setHeaderText("KubbaKapp");
        alert.setContentText("KubbaKapp er leikur þar sem tveir leikmenn keppast um að safna stigum á vissum tíma. \n" +
                "Tíminn fer eftir erfileikastigi (létt, miðlungs, erfitt) sem hægt er að velja í stillingum. \n" +
                "Hafa skal varann á sprengjum sem birtast af og til, ef leikmaður rekst á sprengju missir hann 1 af 3 lífum. " +
                "Ef öll líf klárast tapar sá leikmaður leiknum. \n" + "Stigum eru safnað með því að ná kubbum sem, líkt og sprengjurnar, birtast á leikskjánnum. \n" +
                "\n" + "Þegar sigri er náð birtist gluggi sem hefur valmöguleika á að hefja nýjan leik eða hætta.\n" +
                " \n" +
                "KubbaKappar smíðuðu þetta forrit.");
        alert.showAndWait();
    }

}
