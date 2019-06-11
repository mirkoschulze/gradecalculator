package eu.ggnet.calculatorui.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    private BorderPane borderpane;
    private HBox top, bottom;
    private VBox left, center, right;

    private Button close;

    private MainController controller;

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

    @Override
    public void start(Stage stage) throws Exception {
        this.initializeMain(stage);

        this.top = new HBox(stage.getWidth() / 4, this.controller.getClassbookSelection(),
                this.controller.getSubjectSelection(), this.controller.getCalculationSelection());
        this.top.setAlignment(Pos.TOP_CENTER);
        this.top.setPadding(new Insets(5));

        this.left = new VBox(5, this.controller.getTreeTitle(), this.controller.getTree());
        this.left.setAlignment(Pos.CENTER_LEFT);
        this.left.setPadding(new Insets(5));

        this.center = new VBox(5, this.controller.getPresentationTitle(), this.controller.getPresentation(), this.controller.getInstructions());
        this.center.setAlignment(Pos.CENTER);
        this.center.setPadding(new Insets(5));

        this.right = new VBox(5, this.controller.getTableTitle(), this.controller.getTable());
        this.right.setAlignment(Pos.CENTER);
        this.right.setPadding(new Insets(10));

        this.bottom = new HBox(stage.getWidth() / 4, this.controller.getCalculate(), this.controller.getClear(), this.close);
        this.bottom.setAlignment(Pos.BOTTOM_CENTER);
        this.bottom.setPadding(new Insets(10));

        this.borderpane = new BorderPane();
        this.borderpane.setTop(this.top);
        this.borderpane.setLeft(this.left);
        this.borderpane.setCenter(this.center);
        this.borderpane.setRight(this.right);
        this.borderpane.setBottom(this.bottom);

        stage.setScene(new Scene(this.borderpane, 800, 600));
        stage.show();

    }

    private void initializeMain(Stage stage) {
        this.controller = new MainController();
        this.controller.initialize();
        this.close = new Button("Close");
        this.close.setOnAction(e -> {
            if (new ConfirmationStage("Do you really want to close the programm?").display()) {
                stage.close();
            }
        });

    }

}
