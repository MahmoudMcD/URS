/**
 * Created by Tarek Alqaddy on 3/20/2016.
 */
public class CLinkedList implements LinkedList<CNode>{
    int size=0;
    CNode sentinel = new CNode();

    public CLinkedList() {
        sentinel.next = sentinel;
        sentinel.priv = sentinel;
    }

    //Same functions as DLinkedList and SPLInkedList

    public CNode add(String name, int id) {
        CNode newNode = new CNode(name, id, sentinel.next, sentinel);
        if (sentinel.priv == sentinel)
            sentinel.priv = newNode;
        sentinel.next = newNode;
        size++;
        return newNode;
    }
    public CNode getNode(String name){
        CNode temp = sentinel.next;
        while (temp != sentinel){
            if(temp.name.equals(name))
                return temp;
            temp = temp.next;
        }
        return null;
    }
    public CNode getNode(int id){
        CNode temp = sentinel.next;
        while (temp != sentinel){
            if(temp.id == id)
                return temp;
            temp = temp.next;
        }
        return null;
    }
    public CNode[] getNodes(){

        CNode temp = sentinel.next;
        CNode[] arr = new CNode[10];
        int i=0;
        while(temp != sentinel){
            if(i==arr.length){
                arr = getArray(arr);
            }
            arr[i] = temp;
            temp = temp.next;
            i++;
        }
        if (i != arr.length)
        {
            CNode[] newArr = new CNode[i];
            System.arraycopy(arr, 0, newArr, 0, i);
            arr = newArr;
        }
        return arr;
    }
    public void removeNode(String name){
        CNode temp = getNode(name);
        if(temp != null){
            temp.next.priv = temp.priv;
            temp.priv.next = temp.next;
            size--;
        }
    }
    public void removeNode(int id){
        CNode temp = getNode(id);
        if(temp != null){
            temp.next.priv = temp.priv;
            temp.priv.next = temp.next;
            size--;
        }

    }
}