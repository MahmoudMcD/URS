
public class LauncherTest {

    public static void main(String[] args) {
        DLinkedList departments = new DLinkedList();
        SPLinkedList students = new SPLinkedList();
        DNode compu = departments.add("Compu", 1);
        DNode petro = departments.add("Petro", 2);

        students.add("Mahmoud", 3342, "test", compu, 0, 0);
        students.add("Test", 3123, "whatever", petro, 0, 0);

        departments.removeNode("Compu");
        DNode material = departments.add("Material", 3);
        departments.printLinkedList();
        students.printLinkedList();
        students.add("Another", 1, "test2", material, 0, 0);
        students.printLinkedList();
        System.out.println();
        students.removeNode(1);
        students.printLinkedList();
        System.out.println();
        students.removeNode(3342);
        students.printLinkedList();
    }



}
