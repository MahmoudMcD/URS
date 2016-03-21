import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
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


        tree = new TreeView<>(root);
        tree.setShowRoot(false);

        layoutMain = new BorderPane();
        layoutMain.setLeft(tree);

        scene = new Scene(layoutMain, 800, 400);
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
}
