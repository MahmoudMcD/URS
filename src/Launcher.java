/**
 * Created by mcd on 20/03/16.
 * this class is to mange all other classes and handle orders from GUI
 */
public class Launcher {

    private SPLinkedList studentsList;
    private SPLinkedList profList;
    private DLinkedList departmentList;
    //private CLinkedList courseList; //TODO

    public Launcher()
    {
        studentsList = new SPLinkedList();
        profList = new SPLinkedList();
        departmentList = new DLinkedList();
        Seeder.seed(studentsList, profList, departmentList, /*Change it to courses*/ null);
        //TODO;
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

    public SPLinkedList getStudentsList() {
        return studentsList;
    }

    public DLinkedList getDepartmentList() {
        return departmentList;
    }

    public SPLinkedList getProfList() {
        return profList;
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

    private String[] getArray(String[] prevArray)
    {
        int newSize = prevArray.length * 2;
        String[] newArray =  new String[newSize];
        System.arraycopy(prevArray, 0, newArray, 0, prevArray.length);
        return newArray;
    }



}
