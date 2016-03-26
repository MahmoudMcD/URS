import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Tarek Alqaddy on 3/26/2016.
 */
public class CourseInfoWindow {

    //show students in one course
    static TableView<SPNode> studentsInCourseTable;
    static TableColumn<SPNode,String> studentNameInCourse,studentEmailInCourse;
    static TableColumn<SPNode,Integer> studentIdInCourse;

    //show professors in one course
    static TableView<SPNode> profsInCourseTable;
    static TableColumn<SPNode,String> profNameInCourse,profEmailInCourse;
    static TableColumn<SPNode,Integer> profIdInCourse;

    public static ObservableList<SPNode> getStudentsInCourse(CNode course,Launcher app){
        ObservableList<SPNode> students = FXCollections.observableArrayList();
        students.addAll(app.getCourseList().getNode(course.name).getStudents().getNodes());
        return students;
    }

    public static ObservableList<SPNode> getProfsInCourse(CNode course,Launcher app){
        ObservableList<SPNode> profs = FXCollections.observableArrayList();
        profs.addAll(app.getCourseList().getNode(course.name).getProfessors().getNodes());
        return profs;
    }


    public static void showStudentsInCourse(CNode course,Launcher app){

        studentNameInCourse = new TableColumn<>("Name");
        studentNameInCourse.setMinWidth(200);
        studentNameInCourse.setCellValueFactory(new PropertyValueFactory<>("name"));

        studentEmailInCourse = new TableColumn<>("Email");
        studentEmailInCourse.setMinWidth(200);
        studentEmailInCourse.setCellValueFactory(new PropertyValueFactory<>("email"));

        studentIdInCourse = new TableColumn<>("ID");
        studentIdInCourse.setMinWidth(100);
        studentIdInCourse.setCellValueFactory(new PropertyValueFactory<>("id"));

        studentsInCourseTable = new TableView<>();
        studentsInCourseTable.setItems(getStudentsInCourse(course,app));
        studentsInCourseTable.getColumns().addAll(studentNameInCourse,studentEmailInCourse,studentIdInCourse);


        Stage studentsWindow = new Stage();
        studentsWindow.setTitle("Students Of "+ course.getName());
        studentsWindow.setMinWidth(550);
        studentsWindow.setMinHeight(350);

        StackPane lay = new StackPane();

        lay.getChildren().add(studentsInCourseTable);

        Scene studentsScene = new Scene(lay,550,350);

        studentsWindow.setScene(studentsScene);
        studentsWindow.show();
    }

    public static void showProfsInCourse(CNode course,Launcher app){
        profNameInCourse = new TableColumn<>("Name");
        profNameInCourse.setMinWidth(200);
        profNameInCourse.setCellValueFactory(new PropertyValueFactory<>("name"));

        profEmailInCourse = new TableColumn<>("Email");
        profEmailInCourse.setMinWidth(200);
        profEmailInCourse.setCellValueFactory(new PropertyValueFactory<>("email"));

        profIdInCourse = new TableColumn<>("ID");
        profIdInCourse.setMinWidth(100);
        profIdInCourse.setCellValueFactory(new PropertyValueFactory<>("id"));

        profsInCourseTable = new TableView<>();
        profsInCourseTable.setItems(getProfsInCourse(course,app));
        profsInCourseTable.getColumns().addAll(profNameInCourse,profEmailInCourse,profIdInCourse);


        Stage profsWindow = new Stage();
        profsWindow.setTitle("Professors Of "+ course.getName());
        profsWindow.setMinWidth(550);
        profsWindow.setMinHeight(350);

        StackPane lay = new StackPane();

        lay.getChildren().add(profsInCourseTable);

        Scene profsScene = new Scene(lay,550,350);

        profsWindow.setScene(profsScene);
        profsWindow.show();
    }

}
