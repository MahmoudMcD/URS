/**
 * Created by mcd on 20/03/16.
 * this class is used to seed the application
 */
public class Seeder {

    public static void seed(SPLinkedList students, SPLinkedList professors, DLinkedList departments, CLinkedList courses)
    {
        // Departments
        DNode compu = departments.add("Compu", 1);
        DNode electro = departments.add("Electro", 2);
        DNode petro = departments.add("Petro", 3);

        // Students
        students.add("Mahmoud Magdy", 3342, "email", compu, 0, 0);
        students.add("Tarek AlQaddy", 3168, "email", compu, 0, 0);
        students.add("Foo", 3131, "email", electro, 0, 0);
        students.add("Bar", 3333, "email", electro, 0, 0);
        students.add("test", 3132, "email", petro, 0, 0);

        // Professors
        professors.add("Foo", 1, "test", compu, 0, 1);
        professors.add("Bar", 2, "test", compu, 0, 1);
        professors.add("test2", 3, "test", electro, 0, 1);
        professors.add("Test", 4, "test", petro, 0, 1);

        courses.add("Math4",22);
        courses.add("Prog II",25);
        courses.add("Data Structure",28);
        courses.add("Circuits",30);
        courses.getNode(30).addStudent("Foo",students);
        courses.getNode(30).addStudent("Bar",students);



        professors.getNode(4).addCourse(courses.getNode(25),courses,0,0);
        professors.getNode(2).addCourse(courses.getNode(25),courses,0,0);



    }

}