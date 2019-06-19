package eu.ggnet.calculator.ui;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class with main() as fallback and start() as starting point. Defines the
 * layout. Adds control components to container components.
 *
 * @author Mirko Schulze
 */
public class MainStage extends Application {

    private static final String LOCATION = "/fxml/MainScene.fxml";

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Tries to show the primary application {@link Stage}.
     * <p>
     * Catches an {@link IOException} by displaying a new {@link AlertStage}
     * with a respective error message.
     *
     * @param stage Stage
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setScene(new Scene(new FXMLLoader(getClass().getResource(LOCATION)).load()));
            stage.show();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + LOCATION + "\nException:\n" + e.getMessage()).display();
        }

    }

}
