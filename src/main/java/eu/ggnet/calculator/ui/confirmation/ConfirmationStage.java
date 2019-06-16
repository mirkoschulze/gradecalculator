package eu.ggnet.calculator.ui.confirmation;

import eu.ggnet.calculator.ui.AlertStage;
import java.lang.reflect.Field;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    /**
     * Displays a new {@link Stage} with a {@link Label} and 2 {@link Button} to
     * confirm or deny a decision.I
     *
     * @return boolean
     */
    public boolean display() {
        String location = "/fml/ConfirmationScene.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
            Parent root = loader.load();
            Field answer = loader.getController().getClass().getField("answer");

            this.primaryStage.setScene(new Scene(root, 400, 300));
            this.primaryStage.showAndWait();

            return answer.getBoolean(loader.getController());
        } catch (Exception e) {
            new AlertStage("Could not properly load scene.\nCheck value: " + location).display();
            return false;
        }

    }

}
