

/**
 * Created by mcd on 20/03/16.
 * this class is to mange all other classes and handle orders from GUI
 */
public class Launcher {

    private SPLinkedList studentsList;
    private SPLinkedList profList;
    private DLinkedList departmentList;
    private CLinkedList courseList;

    public Launcher()
    {
        studentsList = new SPLinkedList();
        profList = new SPLinkedList();
        departmentList = new DLinkedList();
        courseList = new CLinkedList();
        Seeder.seed(studentsList, profList, departmentList,courseList);
        //studentsList.printLinkedList();
    }

    public String[] getNamesInArray(SPLinkedList linkedList)
    {
        String[] names = new String[10];
        int size = 0;
        for (SPNode student: linkedList.getNodes())
        {
            if (size == names.length)
                names = getArray(names);

            names[size] = student.getName();
            size++;
        }
        return names;
    }

    public String[] getNamesInArray(DLinkedList linkedList)
    {
        String[] names = new String[10];
        int size = 0;
        for (DNode department: linkedList.getNodes())
        {
            if (department == null)
                break;

            if (size == names.length)
                names = getArray(names);

            names[size] = department.getTitle();
            size++;
        }

        if (size != names.length)
        {
            String[] newNames = new String[size];
            System.arraycopy(names,0, newNames,0, size);
            names = newNames;
        }

        return names;
    }

    public String[] getNamesOfStudentsInCourseInArray (CNode course){
        String[] students = new String[10];
        int i;
        if(course.getNoOfStudents()!=0)
            students = getNamesInArray(course.getStudents());
        return students;
    }
    public String[] getNamesOfProfessorsInCourseInArray (CNode course){
        String[] professors = new String[10];
        int i;
        if(course.getNoOfProfessors()!=0)
            professors = getNamesInArray(course.getStudents());
        return professors;
    }
    public void removeCourseFromStudents(CNode course, String[] students,SPLinkedList allStudents,CLinkedList allCourses){
        int i;
        for(i=0;i<students.length;i++){
            if(students[i]== null)
                continue;
            allStudents.getNode(students[i]).removeCourse(course,allCourses,1,1);
        }
    }
    public void removeCourseFromProfessors(CNode course, String[] professors,SPLinkedList allProfessors,CLinkedList allCourses){
        int i;
        for(i=0;i<professors.length;i++){
            if(professors[i]==null)
                continue;
            allProfessors.getNode(professors[i]).removeCourse(course,allCourses,0,1);
        }
    }
    public SPLinkedList getStudentsList() {
        return studentsList;
    }

    public DLinkedList getDepartmentList() {
        return departmentList;
    }

    public SPLinkedList getProfList() {
        return profList;
    }

    public CLinkedList getCourseList() {
        return courseList;
    }

    public void setStudentsList(SPLinkedList studentsList) {
        this.studentsList = studentsList;
    }

    public void setProfList(SPLinkedList profList) {
        this.profList = profList;
    }

    public void setDepartmentList(DLinkedList departmentList) {
        this.departmentList = departmentList;
    }

    public void setCourseList(CLinkedList courseList) {
        this.courseList = courseList;
    }

    private String[] getArray(String[] prevArray)
    {
        int newSize = prevArray.length * 2;
        String[] newArray =  new String[newSize];
        System.arraycopy(prevArray, 0, newArray, 0, prevArray.length);
        return newArray;
    }


}
