/**
 * Created by Tarek Alqaddy on 3/19/2016.
 */

public class CNode {
    public String name;
    public int id;
    private int noOfStudents=0,noOfProfessors=0;
    public DLinkedList departmens;
    public SPLinkedList students;
    public SPLinkedList professors;
    CNode next;
    CNode priv;

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
            allStudentLinkedList.removeNode(student.getId());
            this.students.removeNode(student.getId());
            noOfStudents--;
        }
    }
    public void removeProf(String name,SPLinkedList allProfessorsLinkedList){
        SPNode prof = allProfessorsLinkedList.getNode(name);
        if(prof != null){
            allProfessorsLinkedList.removeNode(prof.getId());
            this.students.removeNode(prof.getId());
            noOfStudents--;
        }
    }
    public String getName(){return this.name;}

    public void setName(String name) {this.name = name;}
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getNoOfStudents() {return noOfStudents;}

    public void setNoOfStudents(int noOfStudents) {this.noOfStudents = noOfStudents;}

    public int getNoOfProfessors() {return noOfProfessors;}

    public void setNoOfProfessors(int noOfProfessors) {this.noOfProfessors = noOfProfessors;}

    public DLinkedList getDepartmens() {return departmens;}

    public void setDepartmens(DLinkedList departmens) {this.departmens = departmens;}

    public SPLinkedList getStudents() {return students;}

    public void setStudents(SPLinkedList students) {this.students = students;}

    public SPLinkedList getProfessors() {return professors;}

    public void setProfessors(SPLinkedList professors) {this.professors = professors;}

    public static CNode returnCopy(CNode course){
        CNode temp = new CNode(course.name,course.id,null,null);
        return temp;
    }

}
