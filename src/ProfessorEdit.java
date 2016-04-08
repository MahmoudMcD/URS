import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by mcd on 08/04/16.
 * Edit professors window class
 */
public class ProfessorEdit {
    static Stage window;
    static Scene scene;
    static GridPane layoutMain;
    static Launcher launcher;

    public static void showWindow(Launcher app, int profId)
    {
        launcher = app;
        window = new Stage();
        VBox layoutCont = new VBox();
        HBox layoutTools = new HBox();
        Button saveButton = new Button("Save");

        SPNode prof = launcher.getProfList().getNode(profId);

        window.setTitle("Edit : "+prof.getName());
        window.initModality(Modality.APPLICATION_MODAL);

        TextField profNameField = new TextField(prof.getName());
        TextField profEmailField = new TextField(prof.getEmail());
        TextField profIdField = new TextField(String.valueOf(prof.getId()));
        TextField profDepIdField = new TextField(String.valueOf(prof.getDepid()));

        Label profNameLabel = new Label("Name : ");
        Label profEmailLabel = new Label("Email : ");
        Label profIdLabel = new Label("ID : ");
        Label profDepIdLabel = new Label("Department ID : ");
        Label coursesLabel = new Label("Courses : ");

        layoutMain = new GridPane();
        layoutMain.setPadding(new Insets(30, 30, 30, 30));
        layoutMain.setVgap(20);
        layoutMain.setHgap(20);

        GridPane.setConstraints(profIdLabel, 0, 0);
        GridPane.setConstraints(profIdField, 0, 1);

        GridPane.setConstraints(profNameLabel, 1, 0);
        GridPane.setConstraints(profNameField, 1, 1);

        GridPane.setConstraints(profEmailLabel, 2, 0);
        GridPane.setConstraints(profEmailField, 2, 1);

        GridPane.setConstraints(profDepIdLabel, 3, 0);
        GridPane.setConstraints(profDepIdField, 3, 1);

        GridPane.setConstraints(saveButton, 3, 2);
        saveButton.setOnAction(e-> saveprofChanges(prof,Integer.parseInt(profIdField.getText()),
                profNameField.getText(), profEmailField.getText(), Integer.parseInt(profDepIdField.getText())));


        GridPane.setConstraints(coursesLabel, 0, 3);

        layoutMain.getChildren().addAll(profIdLabel, profIdField, profNameLabel, profNameField,
                profEmailLabel, profEmailField, profDepIdLabel, profDepIdField,saveButton, coursesLabel);


        TableView<CNode> coursesTable = new TableView<>();

        TableColumn<CNode, String> courseNameColumn = new TableColumn<>("Course Title");
        courseNameColumn.setMinWidth(200);
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<CNode, Integer> courseIdColumn = new TableColumn<>("ID");
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        coursesTable.getItems().addAll(prof.getCourses().getNodes());
        coursesTable.getColumns().addAll(courseIdColumn, courseNameColumn);


        TextField courseIdField = new TextField();
        courseIdField.setPromptText("Course ID");
        courseIdField.setMinWidth(100);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addCourseToprof(prof, Integer.parseInt(courseIdField.getText()), coursesTable));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e-> removeCourseFromprof(prof, coursesTable));

        GUIHelpers.setBox(layoutTools);
        layoutTools.getChildren().addAll(courseIdField,addButton, deleteButton);

        layoutCont.getChildren().addAll(layoutMain, coursesTable, layoutTools);


        scene = new Scene(layoutCont, 800, 700);
        window.setScene(scene);
        window.showAndWait();

    }

    public static void addCourseToprof(SPNode prof, int courseId, TableView<CNode> table)
    {
        prof.addCourse(launcher.getCourseList().getNode(courseId), launcher.getCourseList(), 1, 0);
        table.getItems().add(prof.getCourses().getNode(courseId));
    }

    public static void removeCourseFromprof(SPNode prof, TableView<CNode> table)
    {
        ObservableList<CNode> selectedCourses, allCourses;
        selectedCourses = table.getSelectionModel().getSelectedItems();
        allCourses = table.getItems();
        for(CNode course: selectedCourses)
        {
            prof.removeCourse(course, launcher.getCourseList(), 1, 0);
        }
        selectedCourses.forEach(allCourses::remove);
    }

    public static void saveprofChanges(SPNode prof,int profId, String name, String email, int profDepId)
    {
        prof.setName(name);
        prof.setId(profId);
        prof.setEmail(email);
        prof.setDepartment(launcher.getDepartmentList().getNode(profDepId));
    }
}
