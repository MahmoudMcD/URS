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

    public void addCourse(CNode course){
        courses.add(course.name,course.id);
    }

    public static SPNode returnCopy(SPNode spNode)
    {
        SPNode newNode = new SPNode(spNode.getEmail(), spNode.getName(), spNode.getId(),spNode.getDepartment(),null, null);
        return newNode;
    }
}