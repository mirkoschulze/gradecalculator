package eu.ggnet.gradecalculator.ui.update;

import eu.ggnet.gradecalculator.Utilities;
import eu.ggnet.gradecalculator.model.Grade;
import eu.ggnet.gradecalculator.model.Grade.Mark;
import eu.ggnet.gradecalculator.model.Grade.Subject;
import eu.ggnet.gradecalculator.model.Pupil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Invokes a specified {@link Dialog} to change the {@link Grade} of a selected
 * {@link Pupil}.
 * <p>
 * The entered int value is resolved to a {@link Mark}.This mark plus the chosen
 * {@link Subject} are used to create a new Grade, which is the return value of
 * the constructor, if valid values are entered and the finish button is
 * clicked, else the return value is null.
 * <p>
 * This can be used by calling the {@link Dialog#showAndWait() } method.
 *
 * @author Mirko Schulze
 */
public class UpdateGradeDialog extends Dialog<Grade> {

    private Mark mark;

    public UpdateGradeDialog() {
        this.setTitle("Note ändern");
        this.setHeaderText("Wählen Sie ein Fach und eine Note aus.");

        ComboBox<Subject> selectSubjectBox = new ComboBox<>();
        selectSubjectBox.setItems(FXCollections.observableArrayList(Subject.values()));
        selectSubjectBox.setTooltip(new Tooltip("Wähle ein Schulfach aus."));

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(5));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(selectSubjectBox);

        ToggleGroup tg = new ToggleGroup();
        int count = 0;
        for (Mark value : Mark.values()) {
            RadioButton rb = new RadioButton(value.getMiddleschoolMark());
            rb.setToggleGroup(tg);
            hbox.getChildren().add(rb);
            count++;
            if (count == 4 || count == 8 || count == 12) {
                vbox.getChildren().add(hbox);
                hbox = new HBox(5);
                hbox.setAlignment(Pos.CENTER);
            } else if (count == 16) {
                vbox.getChildren().add(hbox);
            }
        }
        this.getDialogPane().setContent(vbox);

        this.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
        Button finishButton = (Button) this.getDialogPane().lookupButton(ButtonType.FINISH);
        finishButton.addEventFilter(ActionEvent.ACTION, eh -> {
            if (selectSubjectBox.getSelectionModel().getSelectedItem() == null) {
                eh.consume();
                Utilities.alertWarn("Bitte wählen Sie ein Schulfach aus.");
            } else if (tg.getSelectedToggle() == null) {
                eh.consume();
                Utilities.alertWarn("Bitte wählen Sie einen Wert für die Note aus.");
            } else {
                RadioButton rb = (RadioButton) tg.getSelectedToggle();
                this.mark = Utilities.findByMiddleschoolMark(rb.getText());
            }
        });
        
        this.setResultConverter(type -> {
            if (type == ButtonType.FINISH) {
                return new Grade(selectSubjectBox.getSelectionModel().getSelectedItem(), this.mark);
            } else {
                return null;
            }
        });

    }

}
