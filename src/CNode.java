/**
 * Created by Tarek Alqaddy on 3/19/2016.
 */

public class CNode {
    public String name;
    public int id;
    private int noOfStudents=0,noOfProfessors=0;
    DLinkedList departmens;
    SPLinkedList students;
    SPLinkedList professors;
    CNode priv;
    CNode next;

    public CNode(){
        next = null;
        priv = null;
    }

    public CNode(String name,int id,CNode next,CNode priv){
        this.name = name;
        this.id = id;
        this.next = next;
        this.priv = priv;
        this.students = new SPLinkedList();
        this.professors = new SPLinkedList();
    }

    public void addStudent(String name,SPLinkedList allStudentLinkedList){
        noOfStudents++;
        SPNode student = allStudentLinkedList.getNode(name);
        students.add(student,0);
        student.addCourse(this);
    }
    public void addProfessor(String name,SPLinkedList allProfessorsLinkedList){
        noOfProfessors++;
        SPNode prof = allProfessorsLinkedList.getNode(name);
        students.add(prof,0);
        prof.addCourse(this);
    }
    public void removeStudent(String name,SPLinkedList allStudentLinkedList){
        SPNode student = allStudentLinkedList.getNode(name);
        if(student != null){
            allStudentLinkedList.removeNode(student.id);
            this.students.removeNode(student.id);
            noOfStudents--;
        }
    }
    public void removeProf(String name,SPLinkedList allProfessorsLinkedList){
        SPNode prof = allProfessorsLinkedList.getNode(name);
        if(prof != null){
            allProfessorsLinkedList.removeNode(prof.id);
            this.students.removeNode(prof.id);
            noOfStudents--;
        }
    }
}
