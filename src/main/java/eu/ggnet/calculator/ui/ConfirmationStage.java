package eu.ggnet.calculator.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Stage to confirm an user event.
 *
 * @author Mirko Schulze
 */
public class ConfirmationStage {

    //fields
    private boolean answer;

    //container components
    private Stage primaryStage;
    private HBox hbox;
    private BorderPane borderPane;

    //control component
    private final Label stageMessage;
    private Button yesButton, noButton;

    public ConfirmationStage(String stageMessage) {
        this.stageMessage = new Label(stageMessage);
    }

    /**
     * Defines the layout and adds functionality to components.
     *
     * @return boolean
     */
    public boolean display() {
        //layout
        hbox = new HBox(25);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        hbox.getChildren().addAll(yesButton, noButton);

        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));
        borderPane.setTop(this.stageMessage);
        borderPane.setCenter(hbox);

        //components
        primaryStage = new Stage(StageStyle.DECORATED);
        primaryStage.setTitle("Confirmation");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            this.answer = true;
            primaryStage.close();
        });

        noButton = new Button("No");
        noButton.setOnAction(e -> {
            this.answer = false;
            primaryStage.close();
        });

        primaryStage.setScene(new Scene(borderPane, 300, 200));
        primaryStage.showAndWait();

        return this.answer;
    }

}
