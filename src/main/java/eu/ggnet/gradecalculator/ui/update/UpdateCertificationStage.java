package eu.ggnet.gradecalculator.ui.update;

import eu.ggnet.gradecalculator.Utilities;
import eu.ggnet.gradecalculator.model.Certification;
import eu.ggnet.gradecalculator.model.Grade;
import eu.ggnet.gradecalculator.model.Pupil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Setter;

/**
 * Displays a {@link Stage} to set the {@link Certification} at a {@link Pupil}.
 *
 * @author Mirko Schulze
 */
public class UpdateCertificationStage extends Dialog<Certification> {

    @Setter
    private Pupil pupil;

    TextField engInput = new TextField();
    TextField itsInput = new TextField();
    TextField itwInput = new TextField();
    TextField socInput = new TextField();
    TextField devInput = new TextField();
    TextField orgaInput = new TextField();
    List<Grade> grades = new ArrayList<>();

//    public UpdateCertificationStage() {
//        Label engLab = new Label("Fachenglisch: ");
//        Label itsLab = new Label("IT Systeme: ");
//        Label itwLab = new Label("IT Workshor: ");
//        Label socLab = new Label("Wirtschaft und Gesellschaft: ");
//        Label devLab = new Label("Anwendungsentwicklung: ");
//        Label orgaLab = new Label("Organisation u. Geschäftsprozesse: ");
//
//        GridPane grid = new GridPane();
//
//        grid.addColumn(0, engLab);
//        grid.addColumn(0, itsLab);
//        grid.addColumn(0, itwLab);
//        grid.addColumn(0, socLab);
//        grid.addColumn(0, devLab);
//        grid.addColumn(0, orgaLab);
//
//        grid.addColumn(1, engInput);
//        grid.addColumn(1, itsInput);
//        grid.addColumn(1, itwInput);
//        grid.addColumn(1, socInput);
//        grid.addColumn(1, devInput);
//        grid.addColumn(1, orgaInput);
//
//        this.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
//
//        this.setResultConverter(type -> {
//            if (type == ButtonType.FINISH) {
//                return new Certification(pupil, grades);
//            }else{
//                return null;
//            }
//        });
//    }


    private static final String FXML_PATH = "/fxml/UpdateCertificationScene.fxml";

    private final Stage stage;

    public UpdateCertificationStage() {
        this.stage = new Stage();
    }

    /**
     * Tries to display a new {@link Stage} to create a new
     * {@link Certification}.
     * <p>
     * Catches an {@link IOException} by displaying a new AlertStage
     * with a respective error message.
     *
     * @return Certification
     */
    public Optional<Certification> createCertification() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
            Parent root = loader.load();
            UpdateCertificationController controller = loader.getController();

            this.stage.setScene(new Scene(root));
            this.stage.showAndWait();

            return Optional.ofNullable(controller.getCertification());
        } catch (IOException e) {
            Utilities.alertWarn("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_PATH + "\nException message:\n" + e.getMessage());
            return Optional.empty();
        }
    }
}
