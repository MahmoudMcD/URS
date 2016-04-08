public class SPNode
{
    private String name;
    private String email;
    private int id;
    private DNode department;
    private CLinkedList courses;
    public SPNode next;
    public SPNode prev;

    public SPNode()
    {
        email = null;   id = 0;
        name = null;    department = null;
        prev = null;
        next = null;
        courses = null;
    }

    public SPNode(String email, String name, int id, DNode department, SPNode next, SPNode prev) {
        this.email = email;
        this.name = name;
        this.id = id;
        this.department = department;
        this.next = next;
        this.prev = prev;
        courses = new CLinkedList();
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public DNode getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(DNode department) {
        this.department = department;
    }

    public void setId(int id) {
        this.id = id;
    }


    public CLinkedList getCourses() {
        return courses;
    }

    public void setCourses(CLinkedList courses) {
        this.courses = courses;
    }

    /* this function is used to add the course to a student
     * if the withinCourses is set to 0 the function also adds the student to the list of students
     * in the course added
     * if not this means that the add was called on the student list inside the course therefore no need to
     * add it to the list < avoiding infinite loop
     */
    public void addCourse(CNode course,CLinkedList allCourses,int profOrStudent,int withinCourses){
        CNode courseCopy = CNode.returnCopy(course);
        courses.add(courseCopy.name,courseCopy.id);
        if(withinCourses ==0) {
            switch (profOrStudent) {
                case 0:
                    allCourses.getNode(courseCopy.getId()).getProfessors().add(SPNode.returnCopy(this), 0);
                    int k = allCourses.getNode(courseCopy.getId()).getNoOfProfessors();
                    allCourses.getNode(courseCopy.getId()).setNoOfProfessors(k + 1);
                    break;
                case 1:
                    allCourses.getNode(courseCopy.getId()).getStudents().add(SPNode.returnCopy(this), 0);
                    int j = allCourses.getNode(courseCopy.getId()).getNoOfStudents();
                    allCourses.getNode(courseCopy.getId()).setNoOfStudents(j + 1);
                    break;
            }
        }

    }

    // withinCourses here works the Same as addCourse
    public void removeCourse(CNode course,CLinkedList allCourses,int profOrStudent, int withinCourses){
        CNode courseCopy = CNode.returnCopy(course);
        courses.removeNode(courseCopy.name);
        if(withinCourses == 0) {
            switch (profOrStudent) {
                case 0:
                    allCourses.getNode(courseCopy.getId()).getProfessors().removeNode(this.getId());
                    int k = allCourses.getNode(courseCopy.getId()).getNoOfProfessors();
                    allCourses.getNode(courseCopy.getId()).setNoOfProfessors(k - 1);
                    break;
                case 1:
                    allCourses.getNode(courseCopy.getId()).getStudents().removeNode(this.id);
                    int j = allCourses.getNode(courseCopy.getId()).getNoOfStudents();
                    allCourses.getNode(courseCopy.getId()).setNoOfStudents(j - 1);
            }
        }
    }


    /* As the SPNode that represents student/professor is used in many classes that has SPLinkedList in it
     * return copy is used to return a copy from the original SPNode to avoid changing the next,prev in the
     * original node
     */
    public static SPNode returnCopy(SPNode spNode)
    {
        SPNode newNode = new SPNode(spNode.getEmail(), spNode.getName(), spNode.getId(),spNode.getDepartment(),null, null);
        return newNode;
    }

    //Dummy getter for department id used for the GUI
    public int getDepid()
    {
        if (department == null)
            return 0;
        return department.getId();
    }
}