import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by mcd on 22/03/16.
 * application GUI
 */
public class GUI extends Application{

    Stage window;
    Scene scene;
    BorderPane layoutMain;
    MenuBar menu; //TODO: Complete the menu
    TreeView<String> sideTree;


    public static void main(String[] args) {
        launch(args);
    }



    public void start(Stage primaryStage)
    {
        createSideTree(sideTree);
    }

    /* this function is to create a new Tree view with the new data */
    public void createSideTree(TreeView<String> tree)
    {
        TreeItem<String> department = new TreeItem<>("Departments");
        TreeItem<String> students;
    }
}
