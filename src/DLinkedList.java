/**
 * Created by mcd on 18/03/16.
 * linked list class for departments
 */
public class DLinkedList implements LinkedList<DNode>{

    private DNode sentinal;
    private int size;

    public DLinkedList()
    {
        sentinal = new DNode();
        sentinal.next = sentinal;
        sentinal.prev = sentinal;
    }

    // Adding a new Node nothing diff. from the SPNode
    public DNode add(String title, int id)
    {
        DNode newNode = new DNode(title, id, sentinal.next, sentinal);

        DNode temp = sentinal.next;
        sentinal.next = newNode;
        temp.prev = newNode;

        return newNode;
    }

    // Return the node department using title/id
    @Override
    public DNode getNode(String title)
    {
        DNode temp = sentinal.next;
        while(temp != sentinal)
        {
            if ((temp.getTitle()).equals(title))
                return temp;
            temp = temp.next;
        }
        return null;
    }

    @Override
    public DNode getNode(int id)
    {
        DNode temp = sentinal.next;
        while(temp != sentinal)
        {
            if (temp.getId() == id)
                return temp;
            temp = temp.next;
        }
        return null;
    }

    // Return the departments as an array to use it in GUI
    public DNode[] getNodes()
    {
        DNode temp = sentinal.next;
        DNode[] arrayOfSPNodes = new DNode[10];
        int size = 0;

        while(temp != sentinal)
        {
            if (size == arrayOfSPNodes.length)
                arrayOfSPNodes = getArray(arrayOfSPNodes);

            arrayOfSPNodes[size] = temp;

            size++;
            temp = temp.next;
        }

        if (size != arrayOfSPNodes.length)
        {
            DNode[] tempArray = new DNode[size];
            System.arraycopy(arrayOfSPNodes, 0, tempArray, 0, size);
            arrayOfSPNodes = tempArray;
        }

        return arrayOfSPNodes;
    }

    /* The Department also should be set to null in SPNode */
    @Override
    public void removeNode(int id)
    {
        DNode toBeRemovedNode = getNode(id);
        if (toBeRemovedNode != null)
        {
            for (SPNode student : toBeRemovedNode.getStudentList().getNodes()) // <<
            {
                student.setDepartment(null);
            }
            toBeRemovedNode.next.prev = toBeRemovedNode.prev;
            toBeRemovedNode.prev.next = toBeRemovedNode.next;
        }

    }

    // As the title of the departments should be unique !
    public void removeNode(String title)
    {
        DNode toBeRemovedNode = getNode(title);
        if (toBeRemovedNode != null)
        {
            for (SPNode student : toBeRemovedNode.getStudentList().getNodes())
            {
                student.setDepartment(null);
            }
            toBeRemovedNode.next.prev = toBeRemovedNode.prev;
            toBeRemovedNode.prev.next = toBeRemovedNode.next;
        }
    }

    // USed for testing
    public void printLinkedList()
    {
        DNode temp = sentinal.next;
        while (temp != sentinal)
        {
            System.out.println(temp.getTitle());
            temp = temp.next;
        }
    }

}
