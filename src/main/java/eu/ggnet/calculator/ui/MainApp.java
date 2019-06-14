package eu.ggnet.calculator.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * Main class with main() as fallback and start() as starting point. Defines the
 * layout. Adds control components to container components.
 *
 * @author Mirko Schulze
 */
public class MainApp extends Application {

    private MainController controller;

    private GridPane grid;
    private HBox hbox;

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
     * Main method, starts the application and the initial window. Defines the
     * scenes layout.
     *
     * @param stage Stage
     */
    @Override
    public void start(Stage stage) throws Exception {

        initializeMain(stage);

        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);

        List<ColumnConstraints> colCons = new ArrayList<>();
        colCons.add(new ColumnConstraints(10, 100, Double.POSITIVE_INFINITY, Priority.SOMETIMES, HPos.CENTER, true));
        colCons.add(new ColumnConstraints(10, 100, Double.POSITIVE_INFINITY, Priority.SOMETIMES, HPos.CENTER, true));
        colCons.add(new ColumnConstraints(10, 100, Double.POSITIVE_INFINITY, Priority.SOMETIMES, HPos.CENTER, true));
        colCons.add(new ColumnConstraints(10, 100, Double.POSITIVE_INFINITY, Priority.SOMETIMES, HPos.CENTER, true));
        colCons.add(new ColumnConstraints(10, 100, Double.POSITIVE_INFINITY, Priority.SOMETIMES, HPos.CENTER, true));
        colCons.add(new ColumnConstraints(10, 100, Double.POSITIVE_INFINITY, Priority.SOMETIMES, HPos.CENTER, true));
        colCons.add(new ColumnConstraints(10, 100, Double.POSITIVE_INFINITY, Priority.SOMETIMES, HPos.CENTER, true));

        List<RowConstraints> rowCons = new ArrayList<>();
        rowCons.add(new RowConstraints(10, 5, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //empty
        rowCons.add(new RowConstraints(10, 20, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //label
        rowCons.add(new RowConstraints(10, 20, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //comboboxes
        rowCons.add(new RowConstraints(10, 20, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //labels
        rowCons.add(new RowConstraints(10, 200, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //area 1
        rowCons.add(new RowConstraints(10, 100, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //area 2
        rowCons.add(new RowConstraints(10, 30, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //menu

        grid.getColumnConstraints().addAll(colCons);
        grid.getRowConstraints().addAll(rowCons);

        hbox.getChildren().addAll(this.controller.getCalculateButton(),
                this.controller.getCalculateForEachClassbookButton(),
                this.controller.getCalculateForEachSubjectButton(),
                this.controller.getClearButton());
        grid.add(this.controller.getInstructions(), 0, 1, 6, 1);
        grid.add(this.controller.getClassbookSelectionBox(), 0, 2, 2, 1);
        grid.add(this.controller.getPupilsListLabel(), 0, 3, 2, 1);
        grid.add(this.controller.getPupilsListView(), 0, 4, 2, 2);
        grid.add(this.controller.getSubjectSelectionBox(), 2, 2, 2, 1);
        grid.add(this.controller.getCalculationSelectionBox(), 4, 2);
        grid.add(this.controller.getPresentationLabel(), 2, 3);
        grid.add(this.controller.getPresentation(), 2, 4, 3, 2);
        grid.add(this.controller.getGradesListLabel(), 5, 3, 2, 1);
        grid.add(this.controller.getGradesListView(), 5, 4, 2, 1);

        grid.add(hbox, 2, 6, 3, 1);
        grid.add(this.controller.getCloseButton(), 6, 6);

        stage.setScene(new Scene(grid, 900, 800));
        stage.show();

    }

    /**
     * Instantiates and initializes the controller and the container components.
     *
     * @param stage Stage
     */
    private void initializeMain(Stage stage) {
        this.controller = new MainController(stage);
        this.grid = new GridPane();
        this.hbox = new HBox();
        this.controller.initialize();
    }

    /**
     * Opens a new {@link ConfirmationStage} to confirm the closing of a
     * {@link Stage}.
     *
     * @param stage Stage
     */
    private void close() {

    }
}
