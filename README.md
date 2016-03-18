# URS
Classes :
    SPNode : this class is used to represent a student or a professor object.
        Used in: SPLinkedList when adding a new Student/Professor to the list
                 DNode when adding a student/professor to a department <!
    SPLinkedList : this class is used to link instance of Students or Professors
        Used in: Main Program <Up until now it's the LauncherTest> to create a list
                 DLinkedList to create a list of students and Professors of this department
                 *>> DLinkedList and SPLinkedList shares the same SPNode <<*
    DNode : this class is used to represent a Department
        Used in: DLinkedList
                 SPNode to represent the department of each student/professor
    DLinkedList : this class it to link instance of departments
        Used in: Main Program