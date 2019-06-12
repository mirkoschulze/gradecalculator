package eu.ggnet.calculator.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * Main class with main() as fallback and start() as starting point. Defines the
 * layout.
 *
 * @author Administrator
 */
public class MainApp extends Application {

    //controller class
    private MainController controller;

    //container components
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
     * @param stage Primary Stage to show content.
     */
    @Override
    public void start(Stage stage) {

        //initialization
        initializeMain(stage);

        //layout
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
        rowCons.add(new RowConstraints(10, 20, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //labels
        rowCons.add(new RowConstraints(10, 20, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //comboboxes
        rowCons.add(new RowConstraints(10, 20, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //label
        rowCons.add(new RowConstraints(10, 5, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //separator
        rowCons.add(new RowConstraints(10, 200, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //area 1
        rowCons.add(new RowConstraints(10, 100, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //area 2
        rowCons.add(new RowConstraints(10, 5, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //separator
        rowCons.add(new RowConstraints(10, 30, Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, true));  //menu

        grid.getColumnConstraints().addAll(colCons);
        grid.getRowConstraints().addAll(rowCons);

        //components
        hbox.getChildren().addAll(this.controller.getCalculateButton(),
                this.controller.getCalculateForEachClassbookButton(), this.controller.getCalculateForEachSubjectButton(),
                this.controller.getClearButton(), this.controller.getCloseButton());

        grid.add(this.controller.getInstructions(), 0, 1, 6, 1);

        //list of pupils
        grid.add(this.controller.getClassbookSelectionBox(), 0, 2, 2, 1);
        grid.add(this.controller.getPupilsListLabel(), 0, 3, 2, 1);
        grid.add(this.controller.getPupilsList(), 0, 5, 2, 2);

        //drop down boxes
        grid.add(this.controller.getSubjectSelectionBox(), 3, 2);
        grid.add(this.controller.getCalculationSelectionBox(), 4, 2);

        //text
        grid.add(this.controller.getPresentationLabel(), 2, 3);
        grid.add(new Separator(), 2, 4, 3, 1);
        grid.add(this.controller.getPresentation(), 2, 5, 3, 2);
        grid.add(new Separator(), 2, 7, 3, 1);

        //list of grades
        grid.add(this.controller.getGradesListLabel(), 5, 3, 2, 1);
        grid.add(this.controller.getGradesList(), 5, 5, 2, 1);

        //buttons
        grid.add(hbox, 2, 8, 3, 1);
        grid.add(this.controller.getCloseButton(), 6, 8);

        stage.setScene(new Scene(grid, 1000, 800));
        stage.show();
    }

    /**
     * Instantiates and initializes the controller and the container.
     *
     * @param stage Primary Stage to show content.
     */
    private void initializeMain(Stage stage) {
        this.controller = new MainController(stage);
        this.grid = new GridPane();
        this.hbox = new HBox();
        this.controller.initialize();
    }
}
