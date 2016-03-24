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
        SPNode newStudent = SPNode.returnCopy(student);
        students.add(newStudent,0);
        student.addCourse(this,null,1,1);
    }
    public void addProfessor(String name,SPLinkedList allProfessorsLinkedList){
        noOfProfessors++;
        SPNode prof = allProfessorsLinkedList.getNode(name);
        SPNode newProf= SPNode.returnCopy(prof);
        professors.add(newProf,0);
        prof.addCourse(this,null,0,1);
    }
    public void removeStudent(String name,SPLinkedList allStudentLinkedList){
        SPNode student = allStudentLinkedList.getNode(name);
        if(student != null){
            student.removeCourse(this,null,1,1);
            this.students.removeNode(student.getId());
            noOfStudents--;
        }
    }
    public void removeProf(String name,SPLinkedList allProfessorsLinkedList){
        SPNode prof = allProfessorsLinkedList.getNode(name);
        if(prof != null){
            prof.removeCourse(this,null,0,1);
            this.students.removeNode(prof.getId());
            noOfProfessors--;
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