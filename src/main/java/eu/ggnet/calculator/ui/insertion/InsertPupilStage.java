package eu.ggnet.calculator.ui.insertion;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Administrator
 */
public class InsertPupilStage {

    private final Stage primaryStage;

    public InsertPupilStage() {
        this.primaryStage = new Stage();
        this.primaryStage.setTitle("Add a pupil");
        this.primaryStage.initModality(Modality.APPLICATION_MODAL);
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InsertPupilScene.fxml"));
        Parent root = loader.load();

        this.primaryStage.setScene(new Scene(root, 400, 300));
        this.primaryStage.show();
    }

}
