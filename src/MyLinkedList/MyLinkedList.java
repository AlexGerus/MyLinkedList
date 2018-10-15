package MyLinkedList;

import java.util.*;

public class MyLinkedList{

    Node head;
    Node tail;
    int size = 0;

    public void add(int data){

        Node node = new Node(data);
        if (tail == null){
            head = node;
            tail = node;
        }
        else {
            tail.nextNode = node;
            tail = node;
        }
        size++;
    }

    public Node delete(int data){
        Node nodeToReturn = null;
        if (size == 0){
            return null;
        }
        if (size == 1){
            nodeToReturn = head;
            head = null;
            tail = null;
            size--;
            return nodeToReturn;
        }

        Node nodeBeforeNodeToDelete = findNodeBefore(data);
        //case where we need to delete the head
        if(nodeBeforeNodeToDelete.data == 0){
            head = head.nextNode;
            size--;
        }
        else if (nodeBeforeNodeToDelete!=null){
            if (tail.data == data){
                nodeBeforeNodeToDelete.nextNode = null;
                tail = nodeBeforeNodeToDelete;
            }
            else{
                nodeBeforeNodeToDelete.nextNode = nodeBeforeNodeToDelete.nextNode.nextNode;
            }
            size--;
        }

        return null;
    }

    /**
     * If this method returns an empty Node, it means that the element we want to delete
     * is at the head of the linkedlist. Returns null if there is no match, and returns
     * a populated Node if it found a match.
     * @param data
     * @return
     */
    public Node findNodeBefore(int data){
        //check the first element for a match
        if (head.data == data){
            return new Node();
        }
        //assign node as the iterator
        Node node = head;
        //iterate through our LinkedList
        while(head.nextNode != null){
            if (node.nextNode.data == data){
                return node;
            }
            node = node.nextNode;
        }

        return null;
    }

    public Node find(int data){
        if (head == null){
            return null;
        }
        //check the first element for a match
        if (head.data == data){
            return head;
        }
        //assign node as the iterator
        Node node = head;
        //iterate through our LinkedList
        while(head.nextNode != null){
            node = node.nextNode;
            if (node.data == data){
                return node;
            }
        }

        return null;
    }
}
