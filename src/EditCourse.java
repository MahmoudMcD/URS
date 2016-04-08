import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by mcd on 08/04/16.
 */
public class EditCourse {
    static Stage window;
    static Scene scene;
    static GridPane layoutMain;
    static Launcher app;
    static VBox layoutCont;

    public static void showWindow(Launcher app, int courseId)
    {
        CNode course = app.getCourseList().getNode(courseId);
        window = new Stage();
        window.setTitle("Edit : "+ course.getName());
        window.initModality(Modality.APPLICATION_MODAL);

        Label courseIdLabel = new Label("Course Id : ");
        Label courseNameLabel = new Label("Course Title : ");

        TextField courseIdField = new TextField(String.valueOf(course.getId()));
        TextField courseNameField = new TextField(course.getName());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e->saveChanges(course, Integer.parseInt(courseIdField.getText()),
                courseNameField.getText()));


        layoutMain = new GridPane();
        layoutMain = new GridPane();
        layoutMain.setPadding(new Insets(30, 30, 30, 30));
        layoutMain.setVgap(20);
        layoutMain.setHgap(20);

        GridPane.setConstraints(courseIdLabel, 0, 0);
        GridPane.setConstraints(courseIdField, 0, 1);
        GridPane.setConstraints(courseNameLabel, 1, 0);
        GridPane.setConstraints(courseNameField, 1, 1);
        GridPane.setConstraints(saveButton, 1, 2);


        layoutMain.getChildren().addAll(courseIdLabel, courseIdField, courseNameLabel, courseNameField, saveButton);
        scene = new Scene(layoutMain, 800, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void saveChanges(CNode course, int courseId, String courseName)
    {
        if (course != null) {
            course.setId(courseId);
            course.setName(courseName);
        }
    }
}
