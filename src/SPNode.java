
/**
 * Created by mcd on 18/03/16.
 * this class is used for SPLinkedList
 * each contain info of a certain student
 * not used intividualy
 */
public class SPNode {

    private String name;
    private DNode department;
    private String email;
    private int id;
    public CLinkedList courses;
    public SPNode next;
    public SPNode prev;

    // Default Constructor
    public SPNode()
    {
        next = null;
        prev = null;
    }

    // Default Constructor for the sentinal node
    public SPNode(SPNode next, SPNode prev)
    {
        this.next = next;
        this.prev = prev;
        name = null;    email = null;   department = null;
        id = 0;
    }

    // Used Constructor for add method
    public SPNode(SPNode next, SPNode prev, String name, DNode department, String email, int id)
    {
        this.next = next;
        this.prev = prev;
        this.department = department;
        this.name = name;
        this.email = email;
        this.id = id;
    }


    // Getters
    public String getName() {
        return name;
    }

    public DNode getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(DNode department) {
        this.department = department;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addCourse(CNode course){
        courses.add(course.name,course.id);
    }
}
