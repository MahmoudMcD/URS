import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by mcd on 20/03/16.
 * GUI Class .. Start the program from here
 */
public class ApplicationGUI extends Application {
    Stage window;
    Scene scene;
    BorderPane layoutMain;
    TreeView<String> tree;
    Launcher app;
    TableView<DNode> departmentTable;
    TableView<SPNode> insideDepartmentTable;
    HBox departmentTableTools;
    TableColumn<DNode, String> depTitleColumn, depIdColumn, depNoOfStdColumn, depNoOfPrfColumn;
    TableColumn<SPNode, String> spNameColumn, spEmailColumn, spDepartmentColumn;
    TableColumn<SPNode, Integer> spIdColumn;
    TextField depTitleField, depIdField;
    Button depTableAddButton, depTableDeleteButton;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage)
    {
        window = primaryStage;
        window.setTitle("URS Application");
        app = new Launcher();

        // Making the side tree view
        TreeItem<String> root, departments, students, professors, courses;
        root = new TreeItem<>();
        root.setExpanded(true);

        // Making the department branch and leafs
        departments = makeBranch("Departments", root);
        String[] departmentsNames = app.getNamesInArray(app.getDepartmentList());
        for (String department: departmentsNames)
        {
            makeBranch(department, departments);
        }

        // Making the students branch
        students = makeBranch("Students", root);
        // Making the professors branch
        professors = makeBranch("Professors", root);

        // Setting up the tree view
        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
                    switchTable(newValue.getValue());
                });


        // Setting up the table view for departments
        setDepartmentTable();
        //End of the department table


        layoutMain = new BorderPane();
        layoutMain.setLeft(tree);

        scene = new Scene(layoutMain, 900, 500);
        window.setScene(scene);
        window.show();
    }

    public TreeItem<String> makeBranch(String title, TreeItem<String> parent)
    {
        TreeItem<String> newItem = new TreeItem<>(title);
        newItem.setExpanded(true);
        parent.getChildren().add(newItem);
        return newItem;
    }

    public ObservableList<DNode> getDepartments()
    {
        ObservableList<DNode> departments = FXCollections.observableArrayList();
        departments.addAll(app.getDepartmentList().getNodes());
        return departments;
    }

    public void setDepartmentTable()
    {
        depTitleColumn = new TableColumn<>("Title");
        depTitleColumn.setMinWidth(200);
        depTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        depIdColumn = new TableColumn<>("ID");
        depIdColumn.setMinWidth(50);
        depIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        depNoOfStdColumn = new TableColumn<>("Students Count");
        depNoOfStdColumn.setMinWidth(70);
        depNoOfStdColumn.setCellValueFactory(new PropertyValueFactory<>("noOfStudents"));

        depNoOfPrfColumn = new TableColumn<>("Professors Count");
        depNoOfPrfColumn.setMinWidth(70);
        depNoOfPrfColumn.setCellValueFactory(new PropertyValueFactory<>("noOfProfessors"));

        departmentTable = new TableView<>();
        departmentTable.setItems(getDepartments());
        departmentTable.getColumns().addAll(depTitleColumn, depIdColumn,depNoOfStdColumn, depNoOfPrfColumn);

        depTitleField = new TextField();
        depTitleField.setPromptText("Department Title");
        depTitleField.setMinWidth(200);

        depIdField = new TextField();
        depIdField.setPromptText("Department Id");
        depIdField.setMinWidth(100);

        depTableAddButton = new Button("Add");
        depTableAddButton.setOnAction(e -> addDepartment());

        depTableDeleteButton = new Button("Delete");

        departmentTableTools = new HBox();
        departmentTableTools.setSpacing(10);
        departmentTableTools.setPadding(new Insets(10, 10, 10, 10));
        departmentTableTools.getChildren().addAll(depTitleField, depIdField, depTableAddButton, depTableDeleteButton);

    }

    public void switchTable(String tableName)
    {
        if (tableName.equals("Departments")) {
            layoutMain.setCenter(departmentTable);
            layoutMain.setBottom(departmentTableTools);
        }
        else
        {
            for(String departmentName : app.getNamesInArray(app.getDepartmentList()))
            {
                if(departmentName.equals(tableName))
                {
                    switchToDepartment(tableName);
                    return;
                }
            }
        }

    }

    public void addDepartment()
    {
        String title = depTitleField.getText();
        int id = Integer.parseInt(depIdField.getText());
        DNode newDepartment = app.getDepartmentList().add(title, id);
        departmentTable.getItems().add(newDepartment);
        depTitleField.clear();
        depIdField.clear();
    }


    public void switchToDepartment(String departmentName)
    {
        insideDepartmentTable = new TableView<>();
        DNode department = app.getDepartmentList().getNode(departmentName);
        spNameColumn = new TableColumn<>("Student Name");
        spNameColumn.setMinWidth(200);
        spNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        spIdColumn = new TableColumn<>("Id");
        spIdColumn.setMinWidth(50);
        spIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        spEmailColumn = new TableColumn<>("Email");
        spEmailColumn.setMinWidth(200);
        spEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        insideDepartmentTable.getItems().addAll(department.getStudentList().getNodes());
        insideDepartmentTable.getColumns().addAll(spNameColumn, spIdColumn, spEmailColumn);

        layoutMain.setCenter(insideDepartmentTable);

    }
}
