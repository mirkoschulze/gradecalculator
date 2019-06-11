/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui.ui;

import eu.ggnet.calculatorui.Generator;
import eu.ggnet.calculatorui.calculation.CalculatedGrade;
import eu.ggnet.calculatorui.calculation.Calculator;
import eu.ggnet.calculatorui.model.Certification;
import eu.ggnet.calculatorui.model.Classbook;
import eu.ggnet.calculatorui.model.Grade.Subject;
import eu.ggnet.calculatorui.model.Pupil;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lombok.Getter;

/**
 *
 * @author Administrator
 */
@Getter
public class MainController {

    private List<Classbook> classbooks;
    private CalculatedGrade calculatedGrade;
    private ComboBox<Classbook> classbookSelection;
    private ComboBox<Subject> subjectSelection;
    private ComboBox<String> calculationSelection;
    private Button calculate;
    private Button clear;
    private Text presentation;
    private Label presentationTitle;
    private Label instructions;
    private Label treeTitle;
    private TreeView tree;
    private Label tableTitle;
    private TableView table;

    public Classbook getSelectedClassbook() {
        return this.classbookSelection.getSelectionModel().getSelectedItem();
    }

    public Subject getSelectedSubject() {
        return this.subjectSelection.getSelectionModel().getSelectedItem();
    }

    public String getSelectedCalculation() {
        return this.calculationSelection.getSelectionModel().getSelectedItem();
    }

    public void initialize() {
        this.classbooks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.classbooks.add(Generator.generateClassbook());
        }
        for (Classbook classbook : this.classbooks) {
            for (Pupil pupil : classbook.getPupils()) {
                pupil.setCertification(Generator.generateCertification(pupil));
            }
        }

        this.classbookSelection = new ComboBox<>(FXCollections.observableArrayList(this.classbooks));
        this.classbookSelection.setPromptText("Select a schoolclass");
        this.classbookSelection.getSelectionModel().selectedItemProperty().addListener((change, oldValue, newValue) -> {
            this.updateTable();
            System.out.println(oldValue);
            System.out.println(newValue);
        });

        this.subjectSelection = new ComboBox<>(FXCollections.observableArrayList(Subject.values()));
        this.subjectSelection.setPromptText("Select a subject");

        this.calculationSelection = new ComboBox<>(FXCollections.observableArrayList());
        this.calculationSelection.getItems().addAll("Average", "Accumulation");
        this.calculationSelection.setPromptText("Select a calculation");

        this.presentationTitle = new Label("Calculation view:");
        this.presentation = new Text();

        this.calculate = new Button("Calculate");
        this.calculate.setOnAction(e -> this.calculateAndPresent());

        this.clear = new Button("Clear");
        this.clear.setOnAction(e -> this.clear());

        this.instructions = new Label("Select a class, a subject and a calculation, then click on 'Calculate'. Click on 'Clear' to reset the text area.");

        this.treeTitle = new Label("Class view:");
        this.buildTree();

        this.tableTitle = new Label("Detailed view:");
        this.buildTable();
    }

    public void calculateAndPresent() {
        if (this.isReady()) {
            this.calculate();
            this.present();
        }
    }

    private boolean isReady() {
        return this.calculationSelection.getSelectionModel().getSelectedItem() != null
                && this.subjectSelection.getSelectionModel().getSelectedItem() != null
                && this.classbookSelection.getSelectionModel().getSelectedItem() != null;
    }

    private void calculate() {
        switch (this.calculationSelection.getSelectionModel().getSelectedItem()) {
            case "Average":
                this.calculatedGrade = Calculator.calculateAverageGrade(this.getSelectedClassbook(), this.getSelectedSubject());
                break;
            case "Accumulation":
                this.calculatedGrade = Calculator.calculateAccumulatedGrade(this.getSelectedClassbook(), this.getSelectedSubject());
                break;
        }
    }

    private void present() {
        StringBuilder sb = new StringBuilder(this.presentation.getText());
        sb.append(this.calculatedGrade.toEnhancedLine()).append("\n");
        this.presentation.setText(new String(sb));
    }

    public void clear() {
        this.presentation.setText("");
    }

    private void buildTree() {
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        for (int i = 0; i < this.classbooks.size(); i++) {
            this.makeBranch(this.classbooks.get(i).getClassbookName(), root);
            for (Pupil pupil : this.classbooks.get(i).getPupils()) {
                this.makeBranch(pupil.toSimpleLine(), root.getChildren().get(i));
            }
        }
        this.tree = new TreeView<>(root);
        this.tree.setShowRoot(false);
    }

    private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        parent.getChildren().add(item);
        return item;
    }

    private void buildTable() {
        TableView certificationTable = new TableView();
        
        TableColumn<Pupil, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Pupil, Certification> certificationColumn = new TableColumn<>("Certification");
        certificationColumn.setCellValueFactory(new PropertyValueFactory<>("certification"));

        this.table = new TableView();
        this.table.getColumns().addAll(nameColumn, certificationColumn);
    }

    private void updateTable() {
        this.table.setItems(FXCollections.observableArrayList(this.getSelectedClassbook().getPupils()));
    }

}
