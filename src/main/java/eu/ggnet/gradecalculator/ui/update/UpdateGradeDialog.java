package eu.ggnet.gradecalculator.ui.update;

import eu.ggnet.gradecalculator.Utilities;
import eu.ggnet.gradecalculator.model.Grade;
import eu.ggnet.gradecalculator.model.Grade.Mark;
import eu.ggnet.gradecalculator.model.Grade.Subject;
import eu.ggnet.gradecalculator.model.Pupil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

/**
 * Invokes a specified Dialog to change the Grade of a selected {@link Pupil}.
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

    private int markValue;

    public UpdateGradeDialog() {
        this.setTitle("Note ändern");
        this.setHeaderText("Wählen Sie ein Fach aus und tragen Sie einen Wert zwischen 0 und 15 ein.\n"
                + "Eine 0 entspricht einer 6, 15 entpsicht einer 1+.");

        ComboBox<Subject> selectSubjectBox = new ComboBox<>();
        selectSubjectBox.setItems(FXCollections.observableArrayList(Subject.values()));
        selectSubjectBox.setTooltip(new Tooltip("Wähle ein Schulfach aus."));

        TextField markInput = new TextField();
        markInput.setPromptText("7");
        markInput.setTooltip(new Tooltip("Tragen einen gültigen Wert ein, wie z.B. \"12\"."));

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5));
        grid.add(new Label("Schulfach: "), 0, 0);
        grid.add(new Label("Notenwert: "), 0, 1);
        grid.add(selectSubjectBox, 1, 0);
        grid.add(markInput, 1, 1);

        this.getDialogPane().setContent(grid);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
        Button finishButton = (Button) this.getDialogPane().lookupButton(ButtonType.FINISH);
        finishButton.addEventFilter(ActionEvent.ACTION, eh -> {
            if (selectSubjectBox.getSelectionModel().getSelectedItem() == null) {
                eh.consume();
                Utilities.alertWarn("Bitte wählen Sie ein Schulfach aus.");
            } else if (markInput.getText().isEmpty()) {
                eh.consume();
                Utilities.alertWarn("Bitte tragen Sie einen Wert für die Note ein.");
            } else {
                try {
                    markValue = Integer.parseInt(markInput.getText().trim().replaceAll("\\D", ""));
                } catch (NumberFormatException e) {
                    Utilities.alertWarn("Bitte tragen Sie nur Ganzzahlen ein.\n\n"
                            + "Fehlermeldung:\n" + e.getMessage());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    //FIXME - exception wird trotzdem geschmissen
                    Utilities.alertWarn("Bitte tragen Sie einen Wert zwischen 0 und 15 ein."
                            + "\n\nFehlermeldung:\n" + ex.getMessage());
                }
            }
        });

        this.setResultConverter(type -> {
            if (type == ButtonType.FINISH) {
                return new Grade(selectSubjectBox.getSelectionModel().getSelectedItem(), Utilities.createMark(markValue));
            } else {
                return null;
            }
        });

    }

}
