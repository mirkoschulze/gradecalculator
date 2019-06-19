package eu.ggnet.calculator.ui.confirmation;

import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * {@link Stage} to confirm an user decision.
 *
 * @author Mirko Schulze
 */
public class ConfirmationStage {

    private static final String FXML_URL = "/fxml/ConfirmationScene.fxml";

    private final Stage primaryStage;

    public ConfirmationStage() {
        this.primaryStage = new Stage();
        this.primaryStage.setTitle("Confirmation");
    }

    /**
     * Tries to display a new {@link Stage} with a {@link Label} and two
     * {@link Button} components to confirm or deny an user decision.
     * <p>
     * Catches an {@link IOException} by displaying a new {@link AlertStage}
     * with a respective error message.
     *
     * @return boolean
     */
    public boolean display() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_URL));
            Parent root = loader.load();
            ConfirmationController controller = loader.getController();

            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.showAndWait();

            return controller.isAnswer();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_URL + "\nException:\n" + e.getMessage()).display();
            return false;
        }

    }

}
