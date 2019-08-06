package eu.ggnet.gradecalculator.ui.update;

import eu.ggnet.gradecalculator.Utilities;
import eu.ggnet.gradecalculator.model.Certification;
import eu.ggnet.gradecalculator.model.Grade;
import eu.ggnet.gradecalculator.model.Grade.Mark;
import eu.ggnet.gradecalculator.model.Grade.Subject;
import eu.ggnet.gradecalculator.model.Pupil;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import lombok.Setter;

/**
 * Invokes a specified {@link Dialog} to change the {@link Certification} of a
 * selected {@link Pupil}.
 * <p>
 * The entered int values are resolved to instances of {@link Mark}. Creates a
 * new Certification with the entered values, which is the return value of the
 * constructor, if valid values are entered and the finish button is clicked,
 * else the return value is null.
 * <p>
 * This can be used by calling the {@link Dialog#showAndWait() } method.
 *
 * @author Mirko Schulze
 */
public class UpdateCertificationDialog extends Dialog<Certification> {

    @Setter
    private Pupil pupil;
    private List<Grade> grades;

    public UpdateCertificationDialog() {
         this.grades = new ArrayList<>();
         
        setTitle("Zeugnis ändern");
        setHeaderText("Tragen Sie für jedes Schulfach einen Wert zwischen 0 und 15 ein.\n"
                + "Eine 0 entspricht einer 6, 15 entpsicht einer 1+");

        TextField engInput = new TextField();
        engInput.setPromptText("15");
        engInput.setTooltip(new Tooltip("Trage einen gültigen Wert ein, z.B. \"14\""));
        TextField itsInput = new TextField();
        itsInput.setPromptText("13");
        itsInput.setTooltip(new Tooltip("Trage einen gültigen Wert ein, z.B. \"12\""));
        TextField itwInput = new TextField();
        itwInput.setPromptText("11");
        itwInput.setTooltip(new Tooltip("Trage einen gültigen Wert ein, z.B. \"10\""));
        TextField aeInput = new TextField();
        aeInput.setPromptText("9");
        aeInput.setTooltip(new Tooltip("Trage einen gültigen Wert ein, z.B. \"7\""));
        TextField wugInput = new TextField();
        wugInput.setPromptText("6");
        wugInput.setTooltip(new Tooltip("Trage einen gültigen Wert ein, z.B. \"5\""));
        TextField orgaInput = new TextField();
        orgaInput.setPromptText("4");
        orgaInput.setTooltip(new Tooltip("Trage einen gültigen Wert ein, z.B. \"3\""));

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5));
        grid.add(new Label(Subject.values()[0].getTheme() + ": "), 0, 0);
        grid.add(new Label(Subject.values()[1].getTheme() + ": "), 0, 1);
        grid.add(new Label(Subject.values()[2].getTheme() + ": "), 0, 2);
        grid.add(new Label(Subject.values()[3].getTheme() + ": "), 0, 3);
        grid.add(new Label(Subject.values()[4].getTheme() + ": "), 0, 4);
        grid.add(new Label(Subject.values()[5].getTheme() + ": "), 0, 5);
        grid.add(engInput, 1, 0);
        grid.add(itsInput, 1, 1);
        grid.add(itwInput, 1, 2);
        grid.add(aeInput, 1, 3);
        grid.add(wugInput, 1, 4);
        grid.add(orgaInput, 1, 5);
        this.getDialogPane().setContent(grid);
        
        this.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
        Button finishButton = (Button) this.getDialogPane().lookupButton(ButtonType.FINISH);
        
        finishButton.addEventFilter(ActionEvent.ACTION, eh -> {
            if (engInput.getText().isEmpty() || itsInput.getText().isEmpty()
                    || itwInput.getText().isEmpty() || aeInput.getText().isEmpty()
                    || wugInput.getText().isEmpty() || orgaInput.getText().isEmpty()) {
                eh.consume();
                Utilities.alertWarn("Bitten tragen Sie für alle Felder Werte ein.");
            } else {
                try {
                    this.grades.add(new Grade(Grade.Subject.ENG,
                            Utilities.findByHighschoolMark(Integer.parseInt(engInput.getText().trim().replaceAll("\\D", "")))));
                    this.grades.add(new Grade(Grade.Subject.IT_S,
                            Utilities.findByHighschoolMark(Integer.parseInt(itsInput.getText().trim().replaceAll("\\D", "")))));
                    this.grades.add(new Grade(Grade.Subject.IT_W,
                            Utilities.findByHighschoolMark(Integer.parseInt(itwInput.getText().trim().replaceAll("\\D", "")))));
                    this.grades.add(new Grade(Grade.Subject.AE,
                            Utilities.findByHighschoolMark(Integer.parseInt(aeInput.getText().trim().replaceAll("\\D", "")))));
                    this.grades.add(new Grade(Grade.Subject.WUG,
                            Utilities.findByHighschoolMark(Integer.parseInt(wugInput.getText().trim().replaceAll("\\D", "")))));
                    this.grades.add(new Grade(Grade.Subject.ORGA,
                            Utilities.findByHighschoolMark(Integer.parseInt(orgaInput.getText().trim().replaceAll("\\D", "")))));
                } catch (NumberFormatException | NullPointerException e) {
                    eh.consume();
                    Utilities.alertWarn("Bitte geben Sie eine Ganzzahl zwischen 0 und 15 ein."
                            + "\n\nFehlermeldung:\n" + e.getMessage());
                }
            }
        });
        
        this.setResultConverter(type -> {
            if (type == ButtonType.FINISH) {
                return new Certification(this.pupil, this.grades);
            } else {
                return null;
            }
        });

    }

}
