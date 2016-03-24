
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
    TableView<SPNode> insideDepartmentTableStudents, insideDepartmentTableProf;
    
    // courses here
    TableView<CNode> coursesTable;
    TableColumn<CNode,String> courseTitleCol;
    TableColumn<CNode,Integer> courseIdCol,courseNoOfStudentsCol,courseNoOfProfessorsCol;
    HBox coursesTableTools;
    TextField coursesNameTextField,coursesIdTextField;
    Button coursesAdd,coursesDelete;
    // end
    
    HBox departmentTableTools, insideDepartmentTableStudentTool, insideDepartmentTableProfTool;
    TableColumn<DNode, String> depTitleColumn, depIdColumn, depNoOfStdColumn, depNoOfPrfColumn;
    TableColumn<SPNode, String> spNameColumn, spEmailColumn;
    TableColumn<SPNode, Integer> spIdColumn, profNameColumn, profIdColumn, profEmailColumn;
    TextField depTitleField, depIdField, studNameField, studEmailField, studIdField;
    TextField profNameField, profIdField, profEmailField;
    Button depTableAddButton, depTableDeleteButton, insideDepStudAdd, insideDepProfAdd, insideDepStudDelete;
    Button insideDepProfDelete;
    VBox insideDepartmentTableVBox;
    Label insideDepName;

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

        departments = makeBranch("Departments", root);
        students = makeBranch("Students", root);
        professors = makeBranch("Professors", root);
        courses = makeBranch("Courses", root);

        makeBranches(app.getNamesInArray(app.getDepartmentList()), departments);

        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> switchTable(newValue.getValue()));

        // Setting up the table view for departments
        setDepartmentTable();
        //End of the department table
        //for courses
        setCoursesTable();


        layoutMain = new BorderPane();
        layoutMain.setLeft(tree);

        scene = new Scene(layoutMain, 900, 500);
        window.setScene(scene);
        window.show();
    }

    // Functions associated with the tree view
    public TreeItem<String> makeBranch(String title, TreeItem<String> parent)
    {
        TreeItem<String> newItem = new TreeItem<>(title);
        newItem.setExpanded(true);
        parent.getChildren().add(newItem);
        return newItem;
    }

    public void makeBranches(String[] titles, TreeItem<String> parent)
    {
        for(String title: titles)
        {
            makeBranch(title, parent);
        }
    }
    // End of the tree view functions

    public ObservableList<DNode> getDepartments()
    {
        ObservableList<DNode> departments = FXCollections.observableArrayList();
        departments.addAll(app.getDepartmentList().getNodes());
        return departments;
    }
    public ObservableList<CNode> getCourses(){
        ObservableList<CNode> courses = FXCollections.observableArrayList();
        courses.addAll(app.getCourseList().getNodes());
        return  courses;
    }
    public void setCoursesTable(){
        courseTitleCol = new TableColumn<>("Title");
        courseTitleCol.setMinWidth(200);
        courseTitleCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        courseIdCol = new TableColumn<>("ID");
        courseIdCol.setMinWidth(70);
        courseIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        courseNoOfProfessorsCol = new TableColumn<>("#Professors");
        courseNoOfProfessorsCol.setMinWidth(100);
        courseNoOfProfessorsCol.setCellValueFactory(new PropertyValueFactory<>("noOfProfessors"));

        courseNoOfStudentsCol = new TableColumn<>("#Students");
        courseNoOfStudentsCol.setMinWidth(200);
        courseNoOfStudentsCol.setCellValueFactory(new PropertyValueFactory<>("noOfStudents"));

        coursesTable = new TableView<>();
        coursesTable.setItems(getCourses());
        coursesTable.getColumns().addAll(courseTitleCol,courseIdCol,courseNoOfProfessorsCol,courseNoOfStudentsCol);

        coursesAdd = new Button("add");
        coursesDelete = new Button("Delete");
        coursesNameTextField = new TextField();
        coursesIdTextField = new TextField();

        coursesNameTextField.setPromptText("Course Name");
        coursesIdTextField.setPromptText("Course ID");

        coursesNameTextField.setMinWidth(200);
        coursesIdTextField.setMinWidth(100);

        coursesTableTools= new HBox(10);
        coursesTableTools.setPadding(new Insets(10, 10, 10, 10));
        coursesTableTools.getChildren().addAll(coursesNameTextField,coursesIdTextField,coursesAdd,coursesDelete);

        coursesAdd.setOnAction(e -> addCourse());
        coursesDelete.setOnAction(e -> removeCourse());

    }
    public void setDepartmentTable()
    {
        departmentTable = new TableView<>();
        depIdColumn = new TableColumn<>("ID");
        depIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        depIdColumn.setMinWidth(50);

        depTitleColumn = new TableColumn<>("Title");
        depTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        depTitleColumn.setMinWidth(200);

        depNoOfPrfColumn = new TableColumn<>("Professors Count");
        depNoOfPrfColumn.setCellValueFactory(new PropertyValueFactory<>("noOfProfessors"));
        depNoOfPrfColumn.setMinWidth(50);

        depNoOfStdColumn = new TableColumn<>("Students Count");
        depNoOfStdColumn.setCellValueFactory(new PropertyValueFactory<>("noOfStudents"));
        depNoOfStdColumn.setMinWidth(50);

        departmentTable.getItems().addAll(app.getDepartmentList().getNodes());
        departmentTable.getColumns().addAll(depIdColumn, depTitleColumn, depNoOfPrfColumn, depNoOfStdColumn);

        departmentTableTools = new HBox();
        depTitleField = new TextField();
        depTitleField.setPromptText("Department Title");
        depTitleField.setMinWidth(200);

        depIdField = new TextField();
        depIdField.setMinWidth(50);
        depIdField.setPromptText("ID");

    }

    public void switchTable(String tableName)
    {
        if (tableName.equals("Departments")) {
            layoutMain.setCenter(departmentTable);
            layoutMain.setBottom(departmentTableTools);
        }
        else if(tableName.equals("Courses")) {
            layoutMain.setCenter(coursesTable);
            layoutMain.setBottom(coursesTableTools);
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
        for(DNode department: app.getDepartmentList().getNodes())
        {
            System.out.println(department.getTitle());
        }
        depTitleField.clear();
        depIdField.clear();
    }


    public void deleteDepartment()
    {
        ObservableList<DNode> departments, selectedDepartments;
        departments = departmentTable.getItems();
        selectedDepartments = departmentTable.getSelectionModel().getSelectedItems();
        selectedDepartments.forEach(departments::remove);
        for(DNode department: selectedDepartments)
        {
            System.out.println(department.getTitle());
            app.getDepartmentList().removeNode(department.getId());
        }
        System.out.println(app.getDepartmentList());
    }
    
    public void addCourse(){
        String name = coursesNameTextField.getText();
        int id= Integer.parseInt(coursesIdTextField.getText());
        coursesTable.getItems().add(app.getCourseList().add(name,id));
        coursesNameTextField.clear();
        coursesIdTextField.clear();
    }

    public void removeCourse(){
        String name = coursesNameTextField.getText();
        int id = Integer.parseInt(coursesIdTextField.getText());
        System.out.println(app.getStudentsList().getNode("Foo").getCourses().size);
        if(app.getCourseList().getNode(id) == null)
            return;
        CNode course = app.getCourseList().getNode(name);
        String[] studentsInThisCourse = app.getNamesOfStudentsInCourseInArray(course);
        String[] professorsInThisCourse = app.getNamesOfProfessorsInCourseInArray(course);
        app.removeCourseFromStudents(course,studentsInThisCourse,app.getStudentsList(),app.getCourseList());
        app.removeCourseFromProfessors(course,professorsInThisCourse,app.getProfList(),app.getCourseList());
        app.getCourseList().removeNode(name);
        coursesTable.getItems().remove(course);
        coursesNameTextField.clear();
        coursesIdTextField.clear();
        System.out.println(app.getStudentsList().getNode("Foo").getCourses().size);
    }
    public void switchToDepartment(String departmentName)
    {
        insideDepName = new Label(departmentName);
        insideDepartmentTableStudentTool = new HBox();

        insideDepartmentTableStudents = new TableView<>();
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

        insideDepartmentTableStudents.getItems().addAll(department.getStudentList().getNodes());
        insideDepartmentTableStudents.getColumns().addAll(spNameColumn, spIdColumn, spEmailColumn);

        studNameField = new TextField();
        studNameField.setPromptText("Student Name");
        studEmailField = new TextField();
        studEmailField.setPromptText("Email");
        studIdField = new TextField();
        studIdField.setPromptText("ID");

        insideDepStudAdd = new Button("Add");
        //TODO: set action
        insideDepStudAdd.setOnAction(e-> {
            addStudentToDepartment();
        });

        insideDepStudDelete = new Button("Delete");
        //TODO: set delete


        // Professors table
        insideDepartmentTableProf = new TableView<>();
        profNameColumn = new TableColumn<>("Professor Name");
        profNameColumn.setMinWidth(200);
        profNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        profEmailColumn = new TableColumn<>("Email");
        profEmailColumn.setMinWidth(200);
        profEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        profIdColumn = new TableColumn<>("ID");
        profIdColumn.setMinWidth(50);
        profIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        insideDepartmentTableProf.getItems().addAll(department.getProfList().getNodes());
        insideDepartmentTableProf.getColumns().addAll(profNameColumn, profIdColumn, profEmailColumn);
        // End of professors table

        //Students tool
        insideDepartmentTableStudentTool.getChildren().addAll(studNameField, studEmailField, studIdField, insideDepStudAdd
                                        ,insideDepStudDelete);
        insideDepartmentTableStudentTool.setSpacing(10);
        insideDepartmentTableStudentTool.setPadding(new Insets(10, 10, 10, 10));


        //End of students tool

        //Prof tool
        insideDepartmentTableProfTool = new HBox();

        profNameField = new TextField();
        profNameField.setPromptText("Professors Name");
        profNameField.setMinWidth(200);

        profEmailField = new TextField();
        profEmailField.setMinWidth(200);
        profEmailField.setPromptText("Email");

        profIdField = new TextField();
        profIdField.setMinWidth(50);
        profIdField.setPromptText("Id");

        insideDepProfAdd = new Button("Add");
        insideDepProfAdd.setOnAction(e -> addProfToDepartment());
        //TODO: add action
        insideDepProfDelete = new Button("Delete");
        insideDepProfDelete.setOnAction(e -> deleteProfFromDepartment());
        //TODO

        insideDepartmentTableProfTool.getChildren().addAll(profNameField,profEmailField, profIdField ,
                insideDepProfAdd, insideDepProfDelete);
        insideDepartmentTableProfTool.setSpacing(10);
        insideDepartmentTableProfTool.setPadding(new Insets(10, 10, 10, 10));

        //End of prof tool



        insideDepartmentTableVBox = new VBox();
        insideDepartmentTableVBox.getChildren().addAll(insideDepartmentTableStudents, insideDepartmentTableStudentTool,
                insideDepartmentTableProf, insideDepartmentTableProfTool);
        layoutMain.setCenter(insideDepartmentTableVBox);

    }

    public void addStudentToDepartment()
    {
        String name = studNameField.getText();
        String email = studEmailField.getText();
        int id = Integer.parseInt(studIdField.getText());
        DNode department = app.getDepartmentList().getNode(insideDepName.getText());
         SPNode student = app.getStudentsList().add(name, id, email, department, 0, 0);
        insideDepartmentTableStudents.getItems().add(student);
        studNameField.clear();
        studEmailField.clear();
        studIdField.clear();
    }

    public void addProfToDepartment()
    {
        String name = profNameField.getText();
        String email = profEmailField.getText();
        int id = Integer.parseInt(profIdField.getText());
        DNode department = app.getDepartmentList().getNode(insideDepName.getText());
        SPNode professor = app.getStudentsList().add(name, id, email, department, 0, 1);
        insideDepartmentTableProf.getItems().add(professor);
        profNameField.clear();
        profEmailField.clear();
        profIdField.clear();
    }

    public void deleteProfFromDepartment()
    {
        ObservableList<SPNode> selectedProf, allprof;
        allprof = insideDepartmentTableProf.getItems();
        selectedProf = insideDepartmentTableProf.getSelectionModel().getSelectedItems();
        selectedProf.forEach(allprof::remove);
        for(SPNode prof: allprof)
        {
            System.out.println(prof.getName());
            app.getProfList().removeNode(prof.getId());
        }
        app.getProfList().printLinkedList();
    }

}
