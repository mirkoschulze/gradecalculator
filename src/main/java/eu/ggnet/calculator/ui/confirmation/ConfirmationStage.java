package eu.ggnet.calculator.ui.confirmation;

import eu.ggnet.calculator.Utilities;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Display a {@link Stage} to confirm an user decision.
 *
 * @author Mirko Schulze
 */
public class ConfirmationStage {

    private static final String FXML_PATH = "/fxml/ConfirmationScene.fxml";

    private final Stage stage;

    public ConfirmationStage() {
        this.stage = new Stage();
        this.stage.setTitle("Confirmation");
        this.stage.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Tries to display a new {@link Stage} to confirm or deny an user decision.
     * <p>
     * Catches an {@link IOException} by displaying a new AlertStage
     * with a respective error message.
     *
     * @return boolean
     */
    public boolean confirm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
            Parent root = loader.load();
            ConfirmationController controller = loader.getController();

            this.stage.setScene(new Scene(root));
            this.stage.showAndWait();

            return controller.isConfirmed();
        } catch (IOException e) {
            Utilities.alertWarn("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_PATH + "\nException message:\n" + e.getMessage());
            return false;
        }
    }

}
