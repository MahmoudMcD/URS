/**
 * Created by mcd on 17/03/16.
 * this is the linked list class of Students/Professors
 * class will contain interface methods in addition to : TODO
 */
public class SPLinkedList implements LinkedList<SPNode> {

    private SPNode sentinal;
    private int size = 0;


    public SPLinkedList()
    {
        sentinal = new SPNode();
        sentinal.next = sentinal;
        sentinal.prev = sentinal;
    }




    /* when adding a new Students make a new SPNode with info of the student in this SPLinkedList
     * if withinDepartment = 0 this means that we are adding the student outside an instence of DNode (department)
     * this means that we need to add it to the list of students of the department hence lines 32 & 33
     * if it's equal = 1 this means that we are adding it inside DNode
     * meaning that the addStudent method of the DNode had already added it to the list of students
     */
    public SPNode add(String name, int id, String email, DNode department, int withinDepartment) // for the student/professor
    {
        SPNode newNode = new SPNode(sentinal.next, sentinal, name, department, email, id);

        if (department != null && withinDepartment == 0)
            department.addStudent(name, id, email);

        sentinal.next = newNode;

        if (sentinal.prev == sentinal)
            sentinal.prev = newNode;

        return newNode;
    }

    // USed for testing
    public void printLinkedList()
    {
        SPNode temp = sentinal.next;
        while (temp != sentinal)
        {
            System.out.println(temp.name);
            temp = temp.next;
        }
    }

    /* this function checks if a SPNode exist and return the SPNode that
     * contains the info of the desired Student/Professor given the name
     */
    @Override
    public SPNode getNode(String name)
    {
        SPNode temp = sentinal.next;
        while (temp != sentinal)
        {
            if (temp.name.equals(name))
                return temp;
            temp = temp.next;
        }
        return null;
    }

    // given the id
    @Override
    public SPNode getNode(int id)
    {
        SPNode temp = sentinal.next;
        while (temp != sentinal)
        {
            if (temp.id == id)
                return temp;
            temp = temp.next;
        }
        return null;
    }

    /* in case of students/professors having the same name this function will return
     * all students/professors of the name wanted
     */
    public SPNode[] getNodes(String name)
    {
        SPNode temp = sentinal.next;
        SPNode[] arrayOfSPNodes = new SPNode[10];
        int size = 0;

        while(temp != sentinal)
        {
            if (size == arrayOfSPNodes.length)
                arrayOfSPNodes = getArray(arrayOfSPNodes);

            if ((temp.name).equals(name))
            {
                arrayOfSPNodes[size] = temp;
                size++;
            }

            temp = temp.next;
        }

        return arrayOfSPNodes;
    }


    /* Removing Student/Professor using this function
     * ID used to avoid mistakes of choosing the right node
     */
    public void removeNode(int id)
    {
        SPNode toBeRemovedNode = getNode(id);
        if (toBeRemovedNode != null)
        {
            toBeRemovedNode.next.prev = toBeRemovedNode.prev;
            toBeRemovedNode.prev.next = toBeRemovedNode.next;
        }
    }

}
