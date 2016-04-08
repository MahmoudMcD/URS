import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Tarek Alqaddy on 4/8/2016.
 */
public class CourseAddWindow {


    static HBox lay1;
    static GridPane lay2;
    static TextField coursesAddId;
    static Label msg1,msg2;
    static Button addStudentButton,addProfessorButton;

    public static void setWindow(CNode course,Launcher app){

        msg1 = new Label("Enter ID");
        coursesAddId = new TextField();
        addStudentButton = new Button("Add Student");
        addProfessorButton = new Button("Add Professor");
        msg2 = new Label();

        lay2 = new GridPane();
        lay2.setPadding(new Insets(30,30,30,30));
        lay2.setHgap(20);
        lay2.setVgap(20);

        GridPane.setConstraints(msg1,2,0);
        GridPane.setConstraints(coursesAddId,2,1);
        GridPane.setConstraints(addStudentButton,1,2);
        GridPane.setConstraints(addProfessorButton,3,2);
        GridPane.setConstraints(msg2,2,4);

        lay2.getChildren().addAll(msg1,coursesAddId,addStudentButton,addProfessorButton,msg2);

        addStudentButton.setOnAction(e -> addStudent(course,app));
        addProfessorButton.setOnAction(e -> addProfessor(course,app));

        Stage window = new Stage();
        window.setTitle("Add To "+course.getName());
        Scene scene = new Scene(lay2,600,260);

        window.setScene(scene);

        window.show();
    }

    public static void addStudent(CNode course,Launcher app){
        int studentId = Integer.parseInt(coursesAddId.getText());
        SPNode student = app.getStudentsList().getNode(studentId);

        if(student != null){
            if(app.getCourseList().getNode(course.getId()).getStudents().getNode(studentId) != null){
                msg2.setText("Student Already Enrolled");
                return;
            }
            app.getCourseList().getNode(course.getId()).addStudent(student.getName(),app.getStudentsList());

            msg2.setText("Added Successfully");

        }
        else{
            msg2.setText("Student Not Found !!");
        }

    }
    public static void addProfessor(CNode course,Launcher app){
        int professorId = Integer.parseInt(coursesAddId.getText());
        SPNode professor = app.getProfList().getNode(professorId);

        if(professor != null){
            if(app.getCourseList().getNode(course.getId()).getProfessors().getNode(professorId) != null){
                msg2.setText("Professor Already Enrolled");
                return;
            }
            app.getCourseList().getNode(course.getId()).addProfessor(professor.getName(),app.getProfList());
            msg2.setText("Added Successfully");
        }
        else{
            msg2.setText("Professor Not Found !!");
        }

    }

}
