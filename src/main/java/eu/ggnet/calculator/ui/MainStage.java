package eu.ggnet.calculator.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class with main() as fallback and start() as starting point. Defines the
 * layout. Adds control components to container components.
 *
 * @author Mirko Schulze
 */
public class MainStage extends Application {

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
     * Main method, starts the application and the initial window.
     *
     * @param stage Stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = new FXMLLoader(getClass().getResource("/fxml/MainScene.fxml")).load();

        stage.setScene(new Scene(root, 900, 800));
        stage.show();
    }

}
