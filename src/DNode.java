/**
 * Created by mcd on 18/03/16.
 * this class is used to represent the department node with info
 * used both in linked lists and indiviualy
 */
public class DNode
{

    public String title;
    public int id;
    private int noOfStudents;
    private int noOfProfessors;
    private SPLinkedList students;
    private  SPLinkedList professors;

    public DNode next;
    public DNode prev;

    public DNode()
    {
        next = null;
        prev = null;
    }

    public DNode(String title,int id, DNode next, DNode prev)
    {
        this.next = next;
        this.prev = prev;
        this.id = id;
        this.title = title;
        noOfStudents = 0;
        students = new SPLinkedList();
        professors = new SPLinkedList();
    }


    /* mostly wrapper functions using methods form SPLinkedLists
     * addStudent/addProf methods are called in two events
     * 1 - if we call addStudent to a DNode (department)
     * 2 - if we call add to a SPLinkedList giving it the department of the Student/Professor
     * passing 1 to indicate that we are calling add on SPLinkedList within an instence of DNode
     * so not to call addStudent from the SPLinkedList.add
     */
    public SPNode addStudent(String name, int id, String email)
    {
        noOfStudents++;
        return students.add(name, id, email, this, 1);
    }

    public SPNode addProf(String name, int id, String email)
    {
        noOfProfessors++;
        return professors.add(name, id, email, this, 1);
    }

    public SPLinkedList getStudentList()
    {
        return students;
    }

    public SPLinkedList getProfList()
    {
        return professors;
    }

    public void removeStudent(SPNode student)
    {
        students.removeNode(student.id);
    }

    public void removeProf(SPNode prof)
    {
        professors.removeNode(prof.id);
    }


}
