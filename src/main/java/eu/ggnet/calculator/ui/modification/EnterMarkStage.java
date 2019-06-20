package eu.ggnet.calculator.ui.modification;

import eu.ggnet.calculator.model.Grade.Mark;
import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Mirko Schulze
 */
public class EnterMarkStage {

    private static final String FXML_URL = "/fxml/EnterMarkScene.fxml";

    private final Stage stage;

    public EnterMarkStage() {
        this.stage = new Stage();
        this.stage.setTitle("2: enter a mark");
        this.stage.initModality(Modality.APPLICATION_MODAL);
    }

    public Mark enterMark() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_URL));
            Parent root = loader.load();
            EnterMarkController controller = loader.getController();

            this.stage.setScene(new Scene(root));
            this.stage.showAndWait();
            
            return controller.getMark();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_URL + "\nError:" + e.getMessage()).warn();
            return null;
        }
    }

}
