/**
 * Created by mcd on 17/03/16.
 * this interface is for the linkedlist of all 4 entities
 * Department will have : name - linked list of students - linked list of courses - linked list of professors
 * Student/Professors will have : name - id - email - Department - linked list of courses
 * Courses will have: title - description - department - professor - linkedlist of students
 */
public interface LinkedList<Type> {

    // Add To the list method
    //String add(String name); // this is for the department
    //String add(String name, int id, String email, String department); // for the student/professor

    // This function will return one node (the first) with name
    Type getNode(String name);
    // This function will return one node with id
    Type getNode(int id);

    // removes node with given id
    void removeNode(int id);


    default  Type[] getArray(Type[] prevArray)
    {
        int newSize = prevArray.length * 2;
        Type[] newArray = (Type[]) new Object[newSize];
        System.arraycopy(prevArray, 0, newArray, 0, prevArray.length);
        return newArray;
    }


}
