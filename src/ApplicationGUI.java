import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
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
    Label welcome, namesLabel, designedLabel;
    VBox welcomeScene;
    // Departments and inside the department gui elements
    TableView<DNode> departmentTable;
    TableView<SPNode> insideDepartmentTableStudents, insideDepartmentTableProf;
    HBox departmentTableTools, insideDepartmentTableStudentTool, insideDepartmentTableProfTool;
    TableColumn<DNode, String> depTitleColumn, depIdColumn, depNoOfStdColumn, depNoOfPrfColumn;
    TableColumn<SPNode, String> spNameColumn, spEmailColumn;
    TableColumn<SPNode, Integer> spIdColumn, profNameColumn, profIdColumn, profEmailColumn;
    TextField depTitleField, depIdField, studNameField, studEmailField, studIdField;
    TextField profNameField, profIdField, profEmailField;
    Button depTableAddButton, depTableDeleteButton, insideDepStudAdd, insideDepProfAdd, insideDepStudDelete,courseShowStudents,courseShowProfs;
    Button insideDepProfDelete;
    VBox insideDepartmentTableVBox;
    Label insideDepName, noOfStudents, noOfProfs;
    TreeItem<String> root, departments, students, professors, courses;

    // Students list gui elements
    TableView<SPNode> studentsTable;
    HBox studentsTools;
    TableColumn<SPNode, String> studentNameColumn, studentEmailColumn;
    TableColumn<SPNode, Integer> studentIdColumn, studentDepColumn;
    TextField studentNameField, studentIdField, studentEmailField, studentDepId;
    Button studentAddButton, studentDeleteButton;

    //Professor List gui elements
    TableView<SPNode> profsTable;
    HBox profsTools;
    TableColumn<SPNode, String> profsNameColumn, profsEmailColumn;
    TableColumn<SPNode, Integer> profsIdColumn, profDepIdColumn;
    TextField profsNameField, profsIdField, profsEmailField, profDepIdField;
    Button profsAddButton, profsDeleteButton;


    // courses here
    TableView<CNode> coursesTable;
    TableColumn<CNode,String> courseTitleCol;
    TableColumn<CNode,Integer> courseIdCol,courseNoOfStudentsCol,courseNoOfProfessorsCol;
    HBox coursesTableTools;
    TextField coursesNameTextField,coursesIdTextField;
    Button coursesAdd,coursesDelete;
    // end

    //show students in one course
    TableView<SPNode> studentsInCourseTable;
    TableColumn<SPNode,String> studentNameInCourse,studentEmailInCourse;
    TableColumn<SPNode,Integer> studentIdInCourse;

    //show professors in one course
    TableView<SPNode> profsInCourseTable;
    TableColumn<SPNode,String> profNameInCourse,profEmailInCourse;
    TableColumn<SPNode,Integer> profIdInCourse;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage)
    {
        window = primaryStage;
        window.setTitle("URS Application");
        app = new Launcher();

        // Making the side tree view
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


        layoutMain = new BorderPane();

        //Home Screen
        showWelcomeScene();
        // Setting up the table view for departments
        setDepartmentTable();
        //End of the department table
        //for courses
        setCoursesTable();
        //setStudentTable();
        //EditWindows.editStudentWindow(app, 3342);


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

    public void showWelcomeScene()
    {
        welcomeScene = new VBox();
        welcomeScene.setStyle("-fx-background-color: #4B717F;");
        welcome = new Label("University Registration System");
        welcome.setFont(Font.font ("Verdana", 30));
        welcome.setTextFill(Paint.valueOf("white"));
        designedLabel = new Label("Designed By");
        designedLabel.setFont(Font.font("Verdana", 15));
        designedLabel.setTextFill(Paint.valueOf("white"));
        namesLabel = new Label("Mahmoud Magdy       Tarek AlQaddy");
        namesLabel.setFont(Font.font("Verdana", 20));
        namesLabel.setTextFill(Paint.valueOf("white"));
        welcomeScene.getChildren().addAll(welcome, designedLabel, namesLabel);
        welcomeScene.setAlignment(Pos.CENTER);
        welcomeScene.setPadding(new Insets(30, 30, 30, 30));
        welcomeScene.setSpacing(50);
        layoutMain.setCenter(welcomeScene);
    }

    // changed
    public void makeBranches(String[] titles, TreeItem<String> parent)
    {
        parent.getChildren().clear();
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

        depTableAddButton = new Button("Add");
        depTableAddButton.setOnAction(e -> addDepartment());

        depTableDeleteButton = new Button("Delete");
        depTableDeleteButton.setOnAction(e -> deleteDepartment());

        GUIHelpers.setBox(departmentTableTools);
        departmentTableTools.getChildren().addAll(depTitleField, depIdField, depTableAddButton, depTableDeleteButton);
    }

    //For Courses
    public ObservableList<CNode> getCourses(){
        ObservableList<CNode> courses = FXCollections.observableArrayList();
        courses.addAll(app.getCourseList().getNodes());
        return  courses;
    }

    public ObservableList<SPNode> getStudentsInCourse(CNode course){
        ObservableList<SPNode> students = FXCollections.observableArrayList();
        students.addAll(app.getCourseList().getNode(course.name).getStudents().getNodes());
        return students;
    }

    public ObservableList<SPNode> getProfsInCourse(CNode course){
        ObservableList<SPNode> profs = FXCollections.observableArrayList();
        profs.addAll(app.getCourseList().getNode(course.name).getProfessors().getNodes());
        return profs;
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
        courseShowStudents = new Button("Show Students");
        courseShowProfs = new Button("Show Professors");
        coursesNameTextField = new TextField();
        coursesIdTextField = new TextField();

        coursesNameTextField.setPromptText("Course Name");
        coursesIdTextField.setPromptText("Course ID");

        coursesNameTextField.setMinWidth(200);
        coursesIdTextField.setMinWidth(100);

        coursesTableTools= new HBox(10);
        coursesTableTools.setPadding(new Insets(10, 10, 10, 10));
        coursesTableTools.getChildren().addAll(coursesNameTextField,coursesIdTextField,coursesAdd,coursesDelete,courseShowStudents,courseShowProfs);

        coursesAdd.setOnAction(e -> addCourse());
        coursesDelete.setOnAction(e -> removeCourse());

        courseShowStudents.setOnAction(e -> showStudentInOneCourse());
        courseShowProfs.setOnAction(e -> showProfsInOneCourse());

    }

    public void showStudentsInCourse(String courseName){
        CNode course = app.getCourseList().getNode(courseName);

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
        studentsInCourseTable.setItems(getStudentsInCourse(course));
        studentsInCourseTable.getColumns().addAll(studentNameInCourse,studentEmailInCourse,studentIdInCourse);


        Stage studentsWindow = new Stage();
        studentsWindow.setTitle("Students Of "+ courseName);
        studentsWindow.setMinWidth(550);
        studentsWindow.setMinHeight(350);

        StackPane lay = new StackPane();

        lay.getChildren().add(studentsInCourseTable);

        Scene studentsScene = new Scene(lay,550,350);

        studentsWindow.setScene(studentsScene);
        studentsWindow.show();
    }

    public void showProfsInCourse(String courseName){
        CNode course = app.getCourseList().getNode(courseName);

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
        profsInCourseTable.setItems(getProfsInCourse(course));
        profsInCourseTable.getColumns().addAll(profNameInCourse,profEmailInCourse,profIdInCourse);


        Stage profsWindow = new Stage();
        profsWindow.setTitle("Professors Of "+ courseName);
        profsWindow.setMinWidth(550);
        profsWindow.setMinHeight(350);

        StackPane lay = new StackPane();

        lay.getChildren().add(profsInCourseTable);

        Scene profsScene = new Scene(lay,550,350);

        profsWindow.setScene(profsScene);
        profsWindow.show();
    }

    public void switchTable(String tableName)
    {
        if (tableName.equals("Departments")) {
            layoutMain.setCenter(departmentTable);
            layoutMain.setBottom(departmentTableTools);
        }
        else if(tableName.equals("Students"))
        {
            setStudentTable();
            layoutMain.setCenter(studentsTable);
            layoutMain.setBottom(studentsTools);
        }
        else if(tableName.equals("Professors"))
        {
            setProfTable();
            layoutMain.setCenter(profsTable);
            layoutMain.setBottom(profsTools);
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
        makeBranches(app.getNamesInArray(app.getDepartmentList()), departments);
        depTitleField.clear();
        depIdField.clear();
    }


    public void deleteDepartment()
    {
        ObservableList<DNode> departmentsNode, selectedDepartments;
        departmentsNode = departmentTable.getItems();
        selectedDepartments = departmentTable.getSelectionModel().getSelectedItems();


        for(DNode department: selectedDepartments)
        {
            //System.out.println(department.getTitle());
            app.nullStudentsInDep(department.getStudentList());
            app.getDepartmentList().removeNode(department.getId());
        }

        selectedDepartments.forEach(departmentsNode::remove);
        makeBranches(app.getNamesInArray(app.getDepartmentList()), departments);
        //System.out.println(app.getDepartmentList());
    }

    public void switchToDepartment(String departmentName)
    {
        insideDepName = new Label(departmentName);
        insideDepartmentTableStudentTool = new HBox();
        insideDepartmentTableStudents = new TableView<>();
        DNode department = app.getDepartmentList().getNode(departmentName);
        layoutMain.setBottom(departmentTableTools);

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

        insideDepStudAdd.setOnAction(e-> {
            addStudentToDepartment();
        });

        insideDepStudDelete = new Button("Delete");
        insideDepStudDelete.setOnAction(e->deleteStudFromDepartment());



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
        noOfStudents = new Label();
        noOfStudents.setText("Count: "+String.valueOf(department.getNoOfStudents()));
        noOfStudents.setAlignment(Pos.BOTTOM_RIGHT);

        insideDepartmentTableStudentTool.getChildren().addAll(studNameField, studEmailField, studIdField, insideDepStudAdd
                                        ,insideDepStudDelete, noOfStudents);
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
        insideDepProfDelete = new Button("Delete");
        insideDepProfDelete.setOnAction(e -> deleteProfFromDepartment());

        // Prof Tools
        noOfProfs = new Label();
        noOfProfs.setText("Count: "+String.valueOf(department.getNoOfProfessors()));
        noOfProfs.setAlignment(Pos.BASELINE_RIGHT);
        insideDepartmentTableProfTool.getChildren().addAll(profNameField,profEmailField, profIdField ,
                insideDepProfAdd, insideDepProfDelete, noOfProfs);
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

    public void deleteStudFromDepartment() {
        ObservableList<SPNode> selectedStud, allStud;
        selectedStud = insideDepartmentTableStudents.getSelectionModel().getSelectedItems();
        allStud = insideDepartmentTableStudents.getItems();

        for (SPNode student : selectedStud)
        {
            app.getStudentsList().removeNode(student.getId());
            student.getDepartment().removeStudent(student.getId());
        }
        noOfStudents.setText("Count: "+app.getDepartmentList().getNode(insideDepName.getText()).getNoOfStudents());
        selectedStud.forEach(allStud::remove);
    }

    public void addProfToDepartment()
    {
        String name = profNameField.getText();
        String email = profEmailField.getText();
        int id = Integer.parseInt(profIdField.getText());
        DNode department = app.getDepartmentList().getNode(insideDepName.getText());
        SPNode professor = app.getProfList().add(name, id, email, department, 0, 1);
        insideDepartmentTableProf.getItems().add(professor);
        profNameField.clear();
        profEmailField.clear();
        profIdField.clear();
    }

    public void deleteProfFromDepartment()
    {
        ObservableList<SPNode> selectedProf, allProfs;
        selectedProf = insideDepartmentTableProf.getSelectionModel().getSelectedItems();
        allProfs = insideDepartmentTableProf.getItems();

        for (SPNode prof: selectedProf)
        {
            app.getProfList().removeNode(prof.getId());
            prof.getDepartment().removeProf(prof.getId());

            //app.getDepartmentList().getNode(prof.getDepartment().getId()).removeProf(prof.getId());
        }

        noOfProfs.setText("Count: "+app.getDepartmentList().getNode(insideDepName.getText()).getNoOfProfessors());
        selectedProf.forEach(allProfs :: remove);
    }

    public void setStudentTable()
    {
        studentsTable = new TableView<>();

        studentIdColumn = new TableColumn<>("ID");
        studentIdColumn.setMinWidth(50);
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        studentNameColumn = new TableColumn<>("Name");
        studentNameColumn.setMinWidth(200);
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        studentEmailColumn = new TableColumn<>("Email");
        studentEmailColumn.setMinWidth(200);
        studentEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        studentDepColumn = new TableColumn<>("Department");
        studentDepColumn.setMinWidth(100);
        studentDepColumn.setCellValueFactory(new PropertyValueFactory<>("depid"));

        studentsTable.getColumns().addAll(studentIdColumn, studentNameColumn, studentEmailColumn, studentDepColumn);
        studentsTable.getItems().addAll(app.getStudentsList().getNodes());

        // Setting the tool HBOX
        studentsTools = new HBox();

        studentNameField = new TextField();
        studentNameField.setPromptText("Student Name");
        studentNameField.setMinWidth(100);

        studentIdField = new TextField();
        studentIdField.setMaxWidth(70);
        studentIdField.setPromptText("ID");

        studentEmailField = new TextField();
        studentEmailField.setMinWidth(200);
        studentEmailField.setPromptText("Email");

        studentDepId = new TextField();
        studentDepId.setMinWidth(50);
        studentDepId.setPromptText("Department Id");

        studentAddButton = new Button("Add");
        studentAddButton.setOnAction(e->addStudent());

        studentDeleteButton = new Button("Delete");
        studentDeleteButton.setOnAction(e->deleteStudent());

        studentsTools.getChildren().addAll(studentIdField, studentNameField, studentEmailField, studentDepId,
                studentAddButton, studentDeleteButton);
        GUIHelpers.setBox(studentsTools);
    }

    public void addStudent()
    {
        String name = studentNameField.getText();
        String email = studentEmailField.getText();
        int id = Integer.parseInt(studentIdField.getText());
        int departmentId = Integer.parseInt(studentDepId.getText());
        DNode departmentTobeAddedTo = app.getDepartmentList().getNode(departmentId);
        SPNode newStudent = app.getStudentsList().add(name, id, email, departmentTobeAddedTo, 0, 0);
        studentsTable.getItems().add(newStudent);
        studentNameField.clear();   studentEmailField.clear();
        studentIdField.clear();
        studentDepId.clear();
    }

    public void deleteStudent()
    {
        ObservableList<SPNode> selectedStudents, allStudents;
        selectedStudents = studentsTable.getSelectionModel().getSelectedItems();
        allStudents = studentsTable.getItems();

        for (SPNode student: selectedStudents)
        {
            app.getStudentsList().removeNode(student.getId());
            student.getDepartment().removeStudent(student.getId());
        }

        selectedStudents.forEach(allStudents::remove);
    }

    public void setProfTable()
    {
        profsTable = new TableView<>();

        profsIdColumn = new TableColumn<>("ID");
        profsIdColumn.setMinWidth(50);
        profsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        profsNameColumn = new TableColumn<>("Professor Name");
        profsNameColumn.setMinWidth(200);
        profsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        profsEmailColumn = new TableColumn<>("Email");
        profsEmailColumn.setMinWidth(150);
        profsEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        profDepIdColumn = new TableColumn<>("Department");
        profDepIdColumn.setMinWidth(30);
        profDepIdColumn.setCellValueFactory(new PropertyValueFactory<>("depid"));

        profsTable.getColumns().addAll(profsIdColumn, profsNameColumn, profsEmailColumn, profDepIdColumn);
        profsTable.getItems().addAll(app.getProfList().getNodes());


        profsTools = new HBox();

        profsIdField = new TextField();
        profsIdField.setMaxWidth(70);
        profsIdField.setPromptText("ID");

        profsNameField = new TextField();
        profsNameField.setMinWidth(200);
        profsNameField.setPromptText("Professor Name");

        profsEmailField = new TextField();
        profsEmailField.setMinWidth(100);
        profsEmailField.setPromptText("Email");

        profDepIdField = new TextField();
        profDepIdField.setMaxWidth(100);
        profDepIdField.setPromptText("Department ID");

        profsAddButton = new Button("Add");
        profsAddButton.setOnAction(e->addProf());

        profsDeleteButton = new Button("Delete");
        profsDeleteButton.setOnAction(e->deleteProf());

        profsTools.getChildren().addAll(profsIdField, profsNameField, profsEmailField, profDepIdField,
                profsAddButton, profsDeleteButton);
        GUIHelpers.setBox(profsTools);
    }

    public void deleteProf()
    {
        ObservableList<SPNode> selectedProfs, allProfs;
        allProfs = profsTable.getItems();
        selectedProfs = profsTable.getSelectionModel().getSelectedItems();

        for(SPNode prof: selectedProfs)
        {
            app.getProfList().removeNode(prof.getId());
            prof.getDepartment().removeProf(prof.getId());
        }

        selectedProfs.forEach(allProfs::remove);
    }

    public void addProf()
    {
        String profName = profsNameField.getText();
        String profEmail = profsEmailField.getText();
        int profId = Integer.parseInt(profsIdField.getText());
        int depId = Integer.parseInt(profDepIdField.getText());
        DNode departmentToBeAddedTo = app.getDepartmentList().getNode(depId);

        SPNode newProfessor = app.getProfList().add(profName, profId, profEmail, departmentToBeAddedTo, 0, 1);
        profsTable.getItems().add(newProfessor);

        profsNameField.clear();
        profsEmailField.clear();
        profsIdField.clear();
        profDepIdField.clear();
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
        //System.out.println(app.getStudentsList().getNode("Foo").getCourses().size);
    }

    public void showStudentInOneCourse(){
        String name = coursesNameTextField.getText();
        if(app.getCourseList().getNode(name) != null){
            showStudentsInCourse(name);
        }
        coursesNameTextField.clear();
        coursesIdTextField.clear();
    }
    public void showProfsInOneCourse(){
        String name = coursesNameTextField.getText();
        if(app.getCourseList().getNode(name) != null){
            showProfsInCourse(name);
        }
        coursesNameTextField.clear();
        coursesIdTextField.clear();
    }


}
