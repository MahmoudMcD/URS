/**
 * Created by mcd on 20/03/16.
 */
public class Seeder {

    public static void seed(SPLinkedList students, SPLinkedList professors, DLinkedList departments, CLinkedList courses)
    {
        // Departments
        DNode compu = departments.add("Compu", 1);
        DNode electro = departments.add("Electro", 2);
        DNode petro = departments.add("Petro", 3);

        // Students
        students.add("Mahmoud Magdy", 3342, "email", compu, 0);
        students.add("Tarek AlQaddy", 3168, "email", compu, 0);
        students.add("Foo", 3131, "email", electro, 0);
        students.add("Bar", 3333, "email", electro, 0);
        students.add("Baz", 3132, "email", petro, 0);

        // Professors
        professors.add("Foo", 1, "test", compu, 0);
        professors.add("Bar", 2, "test", compu, 0);
        professors.add("Baz", 3, "test", electro, 0);
        professors.add("Test", 4, "test", petro, 0);

        //TODO seed Courses
    }

}
