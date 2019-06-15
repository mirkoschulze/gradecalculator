package eu.ggnet.calculator.ui.confirmation;

import java.lang.reflect.Field;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Stage to confirm an user event.
 *
 * @author Mirko Schulze
 */
public class ConfirmationStage {

    private final Stage primaryStage;

    public ConfirmationStage() {
        this.primaryStage = new Stage();
        this.primaryStage.setTitle("Confirmation");
        this.primaryStage.initModality(Modality.APPLICATION_MODAL);
    }

    public boolean display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConfirmationScene.fxml"));
        Parent root = loader.load();
        Field answer = loader.getController().getClass().getField("answer");

        this.primaryStage.setScene(new Scene(root, 400, 300));
        this.primaryStage.showAndWait();

        return answer.getBoolean(loader.getController());
    }

}
