import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by mcd on 24/03/16.
 */
public class EditWindows {
    static Stage window;
    static Scene scene;
    static GridPane layoutMain;
    static Launcher launcher;
    static Label saveMsg;

    public static void editStudentWindow(Launcher app, int studentId)
    {
        launcher = app;
        window = new Stage();
        VBox layoutCont = new VBox();
        HBox layoutTools = new HBox();
        Button saveButton = new Button("Save");

        SPNode student = app.getStudentsList().getNode(studentId);

        window.setTitle("Edit : "+student.getName());
        window.initModality(Modality.APPLICATION_MODAL);

        TextField studentNameField = new TextField(student.getName());
        TextField studentEmailField = new TextField(student.getEmail());
        TextField studentIdField = new TextField(String.valueOf(student.getId()));
        TextField studentDepIdField = new TextField(String.valueOf(student.getDepid()));

        Label studentNameLabel = new Label("Student Name : ");
        Label studentEmailLabel = new Label("Email : ");
        Label studentIdLabel = new Label("ID : ");
        Label studentDepIdLabel = new Label("Department ID : ");
        Label coursesLabel = new Label("Courses : ");

        layoutMain = new GridPane();
        layoutMain.setPadding(new Insets(30, 30, 30, 30));
        layoutMain.setVgap(20);
        layoutMain.setHgap(20);

        GridPane.setConstraints(studentIdLabel, 0, 0);
        GridPane.setConstraints(studentIdField, 0, 1);

        GridPane.setConstraints(studentNameLabel, 1, 0);
        GridPane.setConstraints(studentNameField, 1, 1);

        GridPane.setConstraints(studentEmailLabel, 2, 0);
        GridPane.setConstraints(studentEmailField, 2, 1);

        GridPane.setConstraints(studentDepIdLabel, 3, 0);
        GridPane.setConstraints(studentDepIdField, 3, 1);

        GridPane.setConstraints(saveButton, 3, 2);
        saveButton.setOnAction(e-> saveStudentChanges(student,Integer.parseInt(studentIdField.getText()),
                studentNameField.getText(), studentEmailField.getText(), Integer.parseInt(studentDepIdField.getText())));


        GridPane.setConstraints(coursesLabel, 0, 3);

        layoutMain.getChildren().addAll(studentIdLabel, studentIdField, studentNameLabel, studentNameField,
                studentEmailLabel, studentEmailField, studentDepIdLabel, studentDepIdField,saveButton, coursesLabel);


        TableView<CNode> coursesTable = new TableView<>();

        TableColumn<CNode, String> courseNameColumn = new TableColumn<>("Course Title");
        courseNameColumn.setMinWidth(200);
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<CNode, Integer> courseIdColumn = new TableColumn<>("ID");
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        coursesTable.getItems().addAll(student.getCourses().getNodes());
        coursesTable.getColumns().addAll(courseIdColumn, courseNameColumn);


        TextField courseIdField = new TextField();
        courseIdField.setPromptText("Course ID");
        courseIdField.setMinWidth(100);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addCourseToStudent(student, Integer.parseInt(courseIdField.getText()), coursesTable));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e-> removeCourseFromStudent(student, coursesTable));

        GUIHelpers.setBox(layoutTools);
        layoutTools.getChildren().addAll(courseIdField,addButton, deleteButton);

        layoutCont.getChildren().addAll(layoutMain, coursesTable, layoutTools);


        scene = new Scene(layoutCont, 800, 700);
        window.setScene(scene);
        //window.showAndWait();
    }

    public static void addCourseToStudent(SPNode student, int courseId, TableView<CNode> table)
    {
        student.addCourse(launcher.getCourseList().getNode(courseId), launcher.getCourseList(), 1, 0);
        table.getItems().add(student.getCourses().getNode(courseId));
    }

    public static void removeCourseFromStudent(SPNode student, TableView<CNode> table)
    {
        ObservableList<CNode> selectedCourses, allCourses;
        selectedCourses = table.getSelectionModel().getSelectedItems();
        allCourses = table.getItems();
        for(CNode course: selectedCourses)
        {
            student.removeCourse(course, launcher.getCourseList(), 1, 0);
        }
        selectedCourses.forEach(allCourses::remove);
    }

    public static void saveStudentChanges(SPNode student,int studentId, String name, String email, int studentDepId)
    {
        student.setName(name);
        student.setId(studentId);
        student.setEmail(email);
        student.setDepartment(launcher.getDepartmentList().getNode(studentDepId));
    }
}
