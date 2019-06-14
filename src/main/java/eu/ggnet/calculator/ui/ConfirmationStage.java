package eu.ggnet.calculator.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Stage to confirm an user event.
 *
 * @author Mirko Schulze
 */
public class ConfirmationStage {

    private boolean answer;

    private final Stage primaryStage;
    private final VBox vbox;
    private final HBox hbox;
    private final Label stageMessage;
    private final Button yesButton, noButton;

    public ConfirmationStage(String stageMessage) {
        this.primaryStage = new Stage(StageStyle.DECORATED);
        this.stageMessage = new Label(stageMessage);
        this.hbox = new HBox(25);
        this.vbox = new VBox(25);
        this.yesButton = new Button("Yes");
        this.noButton = new Button("No");
    }

    /**
     * Defines the layout and adds functionality to components.
     *
     * @return boolean
     */
    public boolean display() {
        this.hbox.setAlignment(Pos.CENTER);
        this.hbox.setPadding(new Insets(10));
        this.hbox.getChildren().addAll(this.yesButton, this.noButton);

        this.vbox.setAlignment(Pos.CENTER);
        this.vbox.setPadding(new Insets(10));
        this.vbox.getChildren().addAll(this.stageMessage, this.hbox);

        this.primaryStage.setTitle("Confirmation");
        this.primaryStage.initModality(Modality.APPLICATION_MODAL);

        this.yesButton.setOnAction(e -> {
            this.answer = true;
            this.primaryStage.close();
        });

        this.noButton.setOnAction(e -> {
            this.answer = false;
            this.primaryStage.close();
        });

        this.primaryStage.setScene(new Scene(this.vbox, 400, 300));
        this.primaryStage.showAndWait();

        return this.answer;
    }

}
