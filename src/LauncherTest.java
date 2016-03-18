
public class LauncherTest {

    public static void main(String[] args) {
        // ok let's walk through this test
        // first things first .. creating a new DLinkedList for departments
        // and a new SPLinkedList for professors
        DLinkedList departments = new DLinkedList();
        SPLinkedList students = new SPLinkedList();

        /* when calling add to DLinkedlist it returns an DNode containing the info of the department
         * remember passing by reference **
         */
        DNode compu = departments.add("Compu", 1);

        /* calling add on SPLinkedList adds the student to the list
         * and because we are calling it outside the DNode we pass 0 so the add Method would call addStudent
         * on the DNode instence if the department given
         */
        students.add("Mahmoud", 3342, "test@yahoo.com", compu, 0);
        students.add("Magdy", 3341, "Test", compu, 0);

        // getting the 3342 from the students linkedlist
        SPNode me2 = students.getNode(3342);
        System.out.println(me2.name);
        // getting the list of students in department and printing it
        SPLinkedList studentsOfCompu = compu.getStudentList();
        SPNode me = studentsOfCompu.getNode(3342);
        me.name = "Men";
        System.out.println("and here is "+me2.name);

        // same thing but using getNode to get the department first
        DNode compu2 = departments.getNode(compu.title);
        studentsOfCompu = compu2.getStudentList();
        studentsOfCompu.printLinkedList();
    }



}
