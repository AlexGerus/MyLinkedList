package MyLinkedList;

public class Node {
    int data;
    Node nextNode;

    public Node(int data){
        this.data = data;
    }
    public Node(){

    }

    @Override
    public String toString(){
        return "" + data;
    }
}
