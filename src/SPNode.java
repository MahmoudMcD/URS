/**
 * Created by mcd on 18/03/16.
 * this class is used for SPLinkedList
 * each contain info of a certain student
 * not used intividualy
 */
public class SPNode {

    public String name;
    public DNode department;
    public String email;
    public int id;
    // TODO: linked list of courses
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
}
