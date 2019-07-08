package eu.ggnet.calculator.ui.update;

import eu.ggnet.calculator.Utilities;
import eu.ggnet.calculator.model.Certification;
import eu.ggnet.calculator.model.Grade;
import eu.ggnet.calculator.model.Pupil;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Displays a {@link Stage} to set a new {@link Grade} at the
 * {@link Certification} at the selected {@link Pupil}.
 *
 * @author Mirko Schulze
 */
public class UpdateGradeStage {

    private static final String FXML_PATH = "/fxml/UpdateGradeScene.fxml";

    private final Stage stage;

    public UpdateGradeStage() {
        this.stage = new Stage();
        this.stage.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Tries to display a new {@link Stage} to create a new {@link Grade}.
     * <p>
     * Catches an {@link IOException} by displaying a new AlertStage
     * with a respective error message.
     *
     * @return Grade
     */
    public Optional<Grade> updateGrade() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
            Parent root = loader.load();
            UpdateGradeController controller = loader.getController();

            this.stage.setScene(new Scene(root));
            this.stage.showAndWait();

            return Optional.ofNullable(controller.getGrade());
        } catch (IOException e) {
            Utilities.alertWarn("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_PATH + "\nException message:\n" + e.getMessage());
            return Optional.empty();
        }
    }

}
