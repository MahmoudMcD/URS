# URS
Classes :
    /t SPNode : this class is used to represent a student or a professor object. \n
       /t /t Used in: SPLinkedList when adding a new Student/Professor to the list \n
            /t /t /t     DNode when adding a student/professor to a department <! \n
    /t SPLinkedList : this class is used to link instance of Students or Professors \n
       /t /t Used in: Main Program <Up until now it's the LauncherTest> to create a list \n
            /t /t /t     DLinkedList to create a list of students and Professors of this department \n
                 *>> DLinkedList and SPLinkedList shares the same SPNode <<* \n
    DNode : this class is used to represent a Department \n
        Used in: DLinkedList \n
                 SPNode to represent the department of each student/professor \n
    DLinkedList : this class it to link instance of departments \n
        Used in: Main Program \n