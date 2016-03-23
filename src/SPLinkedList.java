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
    public SPNode add(String name, int id, String email, DNode department, int withinDepartment, int type) // for the student/professor
    {
        SPNode newNode = new SPNode(email, name, id, department, sentinal.next, sentinal);
        SPNode temp = sentinal.next;
        sentinal.next = newNode;
        temp.prev = newNode;

        if (withinDepartment == 0)
        {
            if (type == 1)
                department.addProf(newNode);
            else
                department.addStudent(newNode);
        }

        return newNode;
    }

    // Add it to the list of students
    public SPNode add(SPNode student, int withinDNode)
    {
        student.next = sentinal.next;
        student.prev = sentinal;
        SPNode temp = sentinal.next;
        sentinal.next = student;
        temp.prev = student;
        return student;
    }

    // USed for testing
    public void printLinkedList()
    {
        SPNode temp = sentinal.next;
        while (temp != sentinal)
        {
            System.out.println(temp.getName());
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
            if (temp.getName().equals(name))
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
            if (temp.getId() == id)
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

            if ((temp.getName()).equals(name))
            {
                arrayOfSPNodes[size] = temp;
                size++;
            }

            temp = temp.next;
        }

        return arrayOfSPNodes;
    }

    //Returns an array of all Students/Prof in the list
    public SPNode[] getNodes()
    {
        SPNode temp = sentinal.next;
        SPNode[] array = new SPNode[10];
        int size = 0;

        while(temp != sentinal)
        {
            if (size == array.length)
                array = getArray(array);

            array[size] = temp;
            temp = temp.next;
            size++;
        }

        if (size != array.length)
        {
            SPNode[] tempArray = new SPNode[size];
            System.arraycopy(array, 0, tempArray, 0, size);
            array = tempArray;
        }

        return array;
    }


    /* Removing Student/Professor using this function
     * ID used to avoid mistakes of choosing the right node
     */
    public void removeNode(int id)
    {
        SPNode toBeRemoved = getNode(id);
        toBeRemoved.next.prev = toBeRemoved.prev;
        toBeRemoved.prev.next = toBeRemoved.next;
        toBeRemoved.next = null;
        toBeRemoved.prev = null;
    }


}
